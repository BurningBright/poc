package cn.burningbright.poc.springboot.cache;

import cn.burningbright.poc.springboot.cache.model.CachedInvocation;
import cn.burningbright.poc.springboot.cache.utils.CacheHelper;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-09 16:01
 */
@Slf4j
public class CustomizedRedisCache extends RedisCache {

    private ReentrantLock lock = new ReentrantLock();

    public CustomizedRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper valueWrapper = super.get(key);
        CachedInvocation cachedInvocation = CacheHelper.getCacheManager().getCachedInvocation();
        long preLoadTimeSecond = cachedInvocation.getMetaData().getPreLoadTimeSecond();

        if (ObjectUtil.isEmpty(valueWrapper) || preLoadTimeSecond <= 0) {
            return valueWrapper;
        }

        String cacheKey = createCacheKey(key);
        RedisTemplate cacheRedisTemplate = CacheHelper.getCacheManager().getCacheRedisTemplate();
        Long ttl = cacheRedisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);

        if (ObjectUtil.isEmpty(ttl) || preLoadTimeSecond <= ttl) {
            return valueWrapper;
        }

        log.info(">>>>>>>>>>> cacheKey：{}, ttl: {},preLoadTimeSecond: {}", cacheKey, ttl, preLoadTimeSecond);

        CacheHelper.getMethodExecutor().execute(() -> {
            lock.lock();
            try {
                CacheHelper.refreshCache(super.getName());
            } catch (Exception e) {
                log.error("{}", e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        });

        return valueWrapper;
    }

}