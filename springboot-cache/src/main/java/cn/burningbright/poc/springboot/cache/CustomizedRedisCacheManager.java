package cn.burningbright.poc.springboot.cache;

import cn.burningbright.poc.springboot.cache.model.CachedInvocation;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-09 15:59
 */
public class CustomizedRedisCacheManager extends RedisCacheManager {


    private Map<String, RedisCacheConfiguration> initialCacheConfigurations;

    private RedisTemplate cacheRedisTemplate;

    private RedisCacheWriter cacheWriter;

    private RedisCacheConfiguration defaultCacheConfiguration;

    protected CachedInvocation cachedInvocation;


    public CustomizedRedisCacheManager(
            RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
            Map<String, RedisCacheConfiguration> initialCacheConfigurations, RedisTemplate cacheRedisTemplate) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
        this.initialCacheConfigurations = initialCacheConfigurations;
        this.cacheRedisTemplate = cacheRedisTemplate;
        this.cacheWriter = cacheWriter;
        this.defaultCacheConfiguration = defaultCacheConfiguration;
    }

    public Map<String, RedisCacheConfiguration> getInitialCacheConfigurations() {
        return initialCacheConfigurations;
    }

    @Override
    protected Collection<RedisCache> loadCaches() {
        List<RedisCache> caches = new LinkedList<>();
        for (Map.Entry<String, RedisCacheConfiguration> entry : getInitialCacheConfigurations().entrySet()) {
            caches.add(createRedisCache(entry.getKey(), entry.getValue()));
        }
        return caches;
    }

    @Override
    public RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        return new CustomizedRedisCache(
                name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfiguration);
    }

    public RedisTemplate getCacheRedisTemplate() {
        return cacheRedisTemplate;
    }

    public RedisCacheConfiguration getDefaultCacheConfiguration() {
        return defaultCacheConfiguration;
    }

    public CachedInvocation getCachedInvocation() {
        return cachedInvocation;
    }


//    @EventListener
    public void doWithCachedInvocationEvent(CachedInvocation cachedInvocation) {
        this.cachedInvocation = cachedInvocation;
    }

}