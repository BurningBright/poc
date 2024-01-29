package cn.burningbright.poc.springboot.demo;


import cn.burningbright.poc.springboot.cache.PocRedisCacheable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @PocRedisCacheable(cacheNames = "customUser", key = "#id",expiredTimeSecond = 20)
    public User getUserFromRedisByCustomAnno(String id){
        log.info("get user with id by custom anno: 【{}】", id);
        return User.builder().id(id).username("墨子").build();
    }

    @PocRedisCacheable(cacheNames = "customUserName", key = "#username",expiredTimeSecond = 20, preloadTimeSecond = 10)
    public User getUserFromRedisByCustomAnnoWithUserName(String username){
        log.info("get user with username by custom anno: 【{}】", username);
        return User.builder().id("123").username(username).build();
    }

}
