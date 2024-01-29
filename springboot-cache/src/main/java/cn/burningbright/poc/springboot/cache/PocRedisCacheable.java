package cn.burningbright.poc.springboot.cache;

import cn.burningbright.poc.springboot.cache.constant.CacheConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-12 14:24
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable(cacheManager = CacheConstant.CUSTOM_CACHE_MANAGER,keyGenerator = CacheConstant.CUSTOM_CACHE_KEY_GENERATOR)
public @interface PocRedisCacheable {

    @AliasFor(annotation = Cacheable.class, attribute = "value")
    String[] value() default {};

    @AliasFor(annotation = Cacheable.class, attribute = "cacheNames")
    String[] cacheNames() default {};

    @AliasFor(annotation = Cacheable.class, attribute = "key")
    String key() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "keyGenerator")
    String keyGenerator() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "cacheResolver")
    String cacheResolver() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "condition")
    String condition() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "unless")
    String unless() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "sync")
    boolean sync() default false;

    long expiredTimeSecond() default 0;

    long preloadTimeSecond() default 0;


}