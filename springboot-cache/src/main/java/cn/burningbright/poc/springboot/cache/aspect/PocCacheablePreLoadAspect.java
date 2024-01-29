package cn.burningbright.poc.springboot.cache.aspect;


import cn.burningbright.poc.springboot.cache.PocRedisCacheable;
import cn.burningbright.poc.springboot.cache.constant.CacheConstant;
import cn.burningbright.poc.springboot.cache.model.CacheMetaData;
import cn.burningbright.poc.springboot.cache.model.CachedInvocation;
import cn.burningbright.poc.springboot.cache.utils.CacheHelper;
import cn.hutool.extra.spring.SpringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
@Order(1)
public class PocCacheablePreLoadAspect {

    @SneakyThrows
    @Around(value = "@annotation(pocRedisCacheable)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, PocRedisCacheable pocRedisCacheable) {
        buildCachedInvocationAndPushlish(proceedingJoinPoint, pocRedisCacheable);
        return proceedingJoinPoint.proceed();
    }

    private void buildCachedInvocationAndPushlish(ProceedingJoinPoint proceedingJoinPoint, PocRedisCacheable pocRedisCacheable) {
        Method method = this.getSpecificmethod(proceedingJoinPoint);
        String[] cacheNames = CacheHelper.getCacheNames(pocRedisCacheable);
        Object targetBean = proceedingJoinPoint.getTarget();
        Object[] arguments = proceedingJoinPoint.getArgs();
        KeyGenerator keyGenerator = SpringUtil.getBean(CacheConstant.CUSTOM_CACHE_KEY_GENERATOR, KeyGenerator.class);
        Object key = keyGenerator.generate(targetBean, method, arguments);
        CachedInvocation cachedInvocation = CachedInvocation.builder()
                .arguments(arguments)
                .targetBean(targetBean)
                .targetMethod(method)
                .metaData(CacheMetaData.builder()
                        .cacheNames(cacheNames)
                        .key(key)
                        .expiredTimeSecond(pocRedisCacheable.expiredTimeSecond())
                        .preLoadTimeSecond(pocRedisCacheable.preloadTimeSecond())
                        .build()
                )
                .build();
//        applicationContext.publishEvent(cachedInvocation);
        CacheHelper.getCacheManager().doWithCachedInvocationEvent(cachedInvocation);
    }

    private Method getSpecificmethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // The method may be on an interface, but we need attributes from the
        // target class. If the target class is null, the method will be
        // unchanged.
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
        if (targetClass == null && pjp.getTarget() != null) {
            targetClass = pjp.getTarget().getClass();
        }
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the
        // original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }


}
