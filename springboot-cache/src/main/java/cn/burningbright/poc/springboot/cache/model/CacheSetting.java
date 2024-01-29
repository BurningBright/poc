package cn.burningbright.poc.springboot.cache.model;

import lombok.Data;

/**
 * @Author: chenguang.lin
 * @Date: 2024-01-16 13:48
 */
@Data
public class CacheSetting {

    private String cacheName;
    private long expiredTimeSecond;
    private long preLoadTimeSecond;

}