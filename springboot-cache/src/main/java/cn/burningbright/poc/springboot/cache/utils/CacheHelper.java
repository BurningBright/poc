package cn.burningbright.poc.springboot.cache.utils;


import cn.burningbright.poc.springboot.cache.CustomizedRedisCacheManager;
import cn.burningbright.poc.springboot.cache.CustomizedRedisExecutor;
import cn.burningbright.poc.springboot.cache.PocRedisCacheable;
import cn.burningbright.poc.springboot.cache.constant.CacheConstant;
import cn.burningbright.poc.springboot.cache.init.CacheExpireTimeInit;
import cn.burningbright.poc.springboot.cache.model.CachedInvocation;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.Map;

/**
 * 缓存过期时间以及过期时缓存自动刷新核心类
 */
@Slf4j
public final class CacheHelper {

    public static CustomizedRedisExecutor getMethodExecutor() {
        return SpringUtil.getBean(CacheConstant.CUSTOM_REDIS_EXECUTOR, CustomizedRedisExecutor.class);
    }

    public static CustomizedRedisCacheManager getCacheManager() {
        return SpringUtil.getBean(CacheConstant.CUSTOM_CACHE_MANAGER, CustomizedRedisCacheManager.class);
    }


    /**
     * {@link CacheExpireTimeInit}
     *
     * @param pocRedisCacheable
     */
    public static void initExpireTime(PocRedisCacheable pocRedisCacheable) {

        String[] cacheNames = getCacheNames(pocRedisCacheable);

        if (ArrayUtil.isEmpty(cacheNames)) {
            return;
        }

        RedisCacheConfiguration defaultConfig = getCacheManager().getDefaultCacheConfiguration();
        Map<String, RedisCacheConfiguration> initialCacheConfigurations = getCacheManager().getInitialCacheConfigurations();
        for (String cacheName : cacheNames) {

            initialCacheConfigurations
                    .computeIfAbsent(cacheName, k ->
                            defaultConfig.entryTtl(Duration.ofSeconds(pocRedisCacheable.expiredTimeSecond())));

        }

    }

    public static void initializeCaches() {
        getCacheManager().initializeCaches();
        log.info(">>>>>>>>>>>> 【{}】 set expireTime finished !", JSON.toJSONString(getCacheManager().getInitialCacheConfigurations()));
    }

    public static String[] getCacheNames(PocRedisCacheable pocRedisCacheable) {
        String[] cacheNames = pocRedisCacheable.cacheNames();
        if (ArrayUtil.isEmpty(cacheNames)) {
            cacheNames = pocRedisCacheable.value();
        }
        return cacheNames;
    }


    public static void refreshCache(String cacheName) {
        boolean isMatchCacheName = isMatchCacheName(cacheName);
        if (!isMatchCacheName) {
            return;
        }

        CachedInvocation cachedInvocation = getCacheManager().getCachedInvocation();
        boolean invocationSuccess;
        Object computed = null;
        try {
            computed = cachedInvocation.invoke();
            invocationSuccess = true;
        } catch (Exception ex) {
            invocationSuccess = false;
            log.error(">>>>>>>>>>>>>>>>> refresh cache fail", ex.getMessage(), ex);
        }

        if (!invocationSuccess) {
            return;
        }
        Cache cache = getCacheManager().getCache(cacheName);
        if (ObjectUtil.isNotEmpty(cache)) {
            Object cacheKey = cachedInvocation.getMetaData().getKey();
            cache.put(cacheKey, computed);
            log.info(">>>>>>>>>>>>>>>>>>>> refresh cache with cacheName-->【{}】，key--> 【{}】 finished !", cacheName, cacheKey);
        }

    }

    private static boolean isMatchCacheName(String cacheName) {
        CachedInvocation cachedInvocation = getCacheManager().getCachedInvocation();
        if (ObjectUtil.isEmpty(cachedInvocation)) {
            log.warn("cachedInvocation is empty");
            return false;
        }
        for (String name : cachedInvocation.getMetaData().getCacheNames()) {
            if (name.equals(cacheName)) {
                return true;
            }
        }
        return true;
    }

}
