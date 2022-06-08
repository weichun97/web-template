package com.itran.fgoc.common.core.aspect;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.itran.fgoc.common.core.annotation.RepeatSubmit;
import com.itran.fgoc.common.core.api.ResultCode;
import com.itran.fgoc.common.core.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@Aspect
public class NoRepeatSubmitAspect  {

   String PREVENT_DUPLICATION_PREFIX = "PREVENT_DUPLICATION_PREFIX";

   @Resource
   private RedisTemplate redisTemplate;
   /**
    * 定义切点
    */
   @Pointcut("@annotation(com.itran.fgoc.common.core.annotation.RepeatSubmit)")
   public void preventDuplication() {}

   @Around("preventDuplication()")
   public Object around(ProceedingJoinPoint joinPoint) {
      Date now = new Date();
      // 缓存的 key
      String redisKey = calRedisKey(joinPoint);

      // 无缓存：非重复请求
      if (!redisTemplate.opsForHash().hasKey(PREVENT_DUPLICATION_PREFIX, redisKey)) {
         return success(joinPoint, redisKey, now);
      }
      // 有缓存
      else {
         Date lastSubmitTime = (Date) redisTemplate.opsForHash().get(PREVENT_DUPLICATION_PREFIX, redisKey);
         // 非重复请求
         long expireTime = getExpireTime(joinPoint);
         if(DateUtil.between(lastSubmitTime, now, DateUnit.SECOND) > expireTime){
            return success(joinPoint, redisKey, now);
         }
         // 重复请求
         else{
            throw new ApiException(ResultCode.REPEAT_SUBMIT);
         }
      }
   }

   /**
    * 获取重复提交显示
    *
    * @param joinPoint
    * @return
    */
   private long getExpireTime(ProceedingJoinPoint joinPoint) {
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
      return annotation.value();
   }

   /**
    * 计算缓存的 key
    *
    * @param joinPoint
    * @return
    */
   private String calRedisKey(ProceedingJoinPoint joinPoint) {
      // 获取请求url
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
              .getRequestAttributes();
      HttpServletRequest request = attributes.getRequest();
      String url = request.getRequestURI();
      String userId = "1";

      // 通过前缀 + url + userId + 函数参数签名 来生成redis上的 key
      Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
      return String.format("%s:%s:%s", url, userId, getMethodSign(method, joinPoint.getArgs()));
   }

   /**
    * 非重复请求，更新缓存并放行
    *
    * @param joinPoint
    * @param redisKey
    * @param redisValue
    * @return
    */
   private Object success(ProceedingJoinPoint joinPoint, String redisKey, Date redisValue) {
      // 设置防重复操作限时标记（前置通知）
      redisTemplate.opsForHash()
              .put(PREVENT_DUPLICATION_PREFIX, redisKey, redisValue);
      try {
         //正常执行方法并返回
         //ProceedingJoinPoint类型参数可以决定是否执行目标方法，
         // 且环绕通知必须要有返回值，返回值即为目标方法的返回值
         return joinPoint.proceed();
      } catch (Throwable throwable) {
         //确保方法执行异常实时释放限时标记(异常后置通知)
         redisTemplate.delete(redisKey);
         throw new ApiException(ResultCode.REPEAT_SUBMIT);
      }
   }

   /**
    * 生成方法标记：采用数字签名算法SHA1对方法签名字符串加签
    *
    * @param method
    * @param args
    * @return
    */
   private String getMethodSign(Method method, Object... args) {
      StringBuilder sb = new StringBuilder(method.toString());
      for (Object arg : args) {
         sb.append(toString(arg));
      }
      return DigestUtil.sha1Hex(sb.toString());
   }

   private String toString(Object arg) {
      if (Objects.isNull(arg)) {
         return "null";
      }
      if (arg instanceof Number) {
         return arg.toString();
      }
      return JSONUtil.toJsonStr(arg);
   }

}
