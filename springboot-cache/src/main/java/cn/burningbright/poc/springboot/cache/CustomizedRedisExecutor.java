package cn.burningbright.poc.springboot.cache;

import cn.burningbright.poc.springboot.cache.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * async注解异步线程池
 * @author chenguang.lin
 * @date 2023-12-21
 */
@Component(CacheConstant.CUSTOM_REDIS_EXECUTOR)
public class CustomizedRedisExecutor extends ThreadPoolTaskExecutor {

    @Value("${lynkco.redis.cache.asyncPool.corePoolSize:0}")
    private Integer corePoolSizeX;

    @Value("${lynkco.redis.cache.asyncPool.waitQueueSize:8}")
    private Integer queueCapacityX;

    @Value("${lynkco.redis.cache.asyncPool.maxPoolSize:16}")
    private Integer maxPoolSizeX;

    @Value("${lynkco.redis.cache.asyncPool.keepAliveTime:60}")
    private Integer keepAliveTimeX;

    @PostConstruct
    public void init() {
        this.setCorePoolSize(corePoolSizeX);
        this.setQueueCapacity(queueCapacityX);
        this.setMaxPoolSize(maxPoolSizeX);
        this.setKeepAliveSeconds(keepAliveTimeX);
        this.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        this.setThreadNamePrefix("redis-cache-async-");
        this.initialize();
    }

}
