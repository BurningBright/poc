package cn.burningbright.poc.springboot.cache;

import cn.burningbright.poc.springboot.cache.constant.CacheConstant;
import cn.burningbright.poc.springboot.demo.DemoApplication;
import cn.burningbright.poc.springboot.demo.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-23 14:18
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CacheTest {

    @Autowired
    UserService userService;

    @Resource(name = CacheConstant.REDIS_TEMPLATE_BEAN_NAME)
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testExpiredTimeSecond() throws InterruptedException {
        String key = "customUser::1";
        redisTemplate.delete(key);

        Long ttl = redisTemplate.getExpire(key);
        assertEquals(-2, ttl);

        // try first
        userService.getUserFromRedisByCustomAnno("1");
        ttl = redisTemplate.getExpire(key);
        log.info("ttl:{}", ttl);
        assertTrue(ttl > 0);

        TimeUnit.SECONDS.sleep(10);
        // try again
        userService.getUserFromRedisByCustomAnno("1");

        TimeUnit.SECONDS.sleep(11);
        ttl = redisTemplate.getExpire(key);
        assertFalse(ttl > 0);
    }

    @Test
    public void testCacheExpiredAndPreFreshByCustomWithUserName() throws InterruptedException {

        String name = "老子";
        String key = "customUserName::" + name;
        redisTemplate.delete(key);

        Long ttl = redisTemplate.getExpire(key);
        assertEquals(-2, ttl);

        // try first
        userService.getUserFromRedisByCustomAnnoWithUserName(name);
        TimeUnit.SECONDS.sleep(5);
        ttl = redisTemplate.getExpire(key);
        log.info("ttl:{}", ttl);
        assertTrue(ttl < 16);

        TimeUnit.SECONDS.sleep(6);
        // try again
        userService.getUserFromRedisByCustomAnnoWithUserName(name);

        TimeUnit.SECONDS.sleep(2);
        ttl = redisTemplate.getExpire(key);
        log.info("ttl:{}", ttl);
        assertFalse(ttl < 16);

    }

}