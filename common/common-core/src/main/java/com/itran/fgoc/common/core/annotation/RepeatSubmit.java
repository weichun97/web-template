package com.itran.fgoc.common.core.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

   /**
    * 防重复操作过期时间（借助redis实现限时控制）
    */
   long value() default 10 ;
}
