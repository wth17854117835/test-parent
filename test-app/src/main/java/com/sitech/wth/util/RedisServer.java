package com.sitech.wth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 17:21
 * @description: Redis
 **/
@Service
public class RedisServer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setStr(String key, String value) {
        setStr(key, value, null);
    }

    public void setStr(String key, String value, Long time) {
        stringRedisTemplate.opsForValue().set(key, value);
        if (time != null) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    public Object getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

}
