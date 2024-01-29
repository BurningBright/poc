package cn.burningbright.poc.springboot.cache;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static cn.burningbright.poc.springboot.cache.constant.CacheConstant.*;

/**
 * 自定义Redis异步刷新配置
 */
@Configuration
@EnableCaching
public class CustomizedRedisAutoConfiguration {

    @Bean(REDIS_CF_BEAN_NAME)
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
//        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
        redisStandaloneConfiguration.setDatabase(0);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(REDIS_TEMPLATE_BEAN_NAME)
    public RedisTemplate<String, Object> cacheRedisTemplate(
            @Qualifier(REDIS_CF_BEAN_NAME) RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 设置RedisTemplate模板API的序列化方式为JSON
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean(CUSTOM_CACHE_MANAGER)
    public CacheManager cacheManager(
            @Qualifier(REDIS_CF_BEAN_NAME) RedisConnectionFactory connectionFactory,
            @Qualifier(REDIS_TEMPLATE_BEAN_NAME) RedisTemplate template) {

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(template.getKeySerializer()))
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()));

        Map<String, RedisCacheConfiguration> initialCacheConfiguration = new HashMap<>();

        return new CustomizedRedisCacheManager(
                redisCacheWriter, defaultCacheConfig, initialCacheConfiguration, template);
    }

    @Bean(CUSTOM_CACHE_KEY_GENERATOR)
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

}
