
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [redis 使用文档](#redis-使用文档)
    - [使用](#使用)
        - [生成缓存和使用](#生成缓存和使用)
        - [删除缓存](#删除缓存)
        - [缓存 key 定义位置](#缓存-key-定义位置)
        - [key 的默认超时时间](#key-的默认超时时间)
        - [自定义 key 的超时时间](#自定义-key-的超时时间)
    - [部署](#部署)
        - [引入 pom 文件](#引入-pom-文件)

<!-- /code_chunk_output -->

# redis 使用文档
## 使用
### 生成缓存和使用

在 `springbean` 的带有返回值的方法上使用 `@Cacheable` 注解, `@Cacheable` 的 `value` 属性就是 `redis 缓存`的 `key`，
缓存的数据就是方法的返回值
```java
@Service
public class TestServiceImpl{

    @Cacheable(value = RedisVar.LIST_USER)
    public List<User> listUser(){
        // 查询代码
        // ...
    }
}
```

### 删除缓存
在 `springbean` 的方法上使用 `@CacheEvict` 注解, 即可删除对应的缓存
```
@Service
public class TestServiceImpl{

    @CacheEvict(value = RedisVar.LIST_USER)
    public void deleteByUserId(Long userId){
        // 删除用户
    }
}
```

### 缓存 key 定义位置
位置：统一定义在 `com.itran.fgoc.common.redis.var.RedisVar` 类中

### key 的默认超时时间
修改 `RedisVar.DEFAULT_TTL` 的值, 默认6小时

### 自定义 key 的超时时间
修改 `RedisVar.TTL_MAP` 的值, 以分钟为单位

## 部署
### 引入 pom 文件
```
<dependency>
    <groupId>com.itran.fgoc</groupId>
    <artifactId>common-redis</artifactId>
</dependency>
```
