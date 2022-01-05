package com.sitech.wth.service.impl;

import com.sitech.wth.bean.Person;
import com.sitech.wth.util.RedisServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 17:25
 * @description: 测试redis
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisServer redisServer;
    @Autowired
    RedisTemplate<String, Object> initRedisTemplate;

    @Test
    public void testRedis() {
        redisServer.setStr("name", "wth");
        System.out.println("===========getName:" + redisServer.getKey("name") + "===========\n");
    }

    @Test
    public void testRedis2() {
        initRedisTemplate.opsForValue().set("name", "zhoucx");
        System.out.println("===========getName:" + initRedisTemplate.opsForValue().get("name") + "===========");
        initRedisTemplate.opsForValue().set("USER_INFO", new Person("wangth",24));
        System.out.println("===========getUser:" + initRedisTemplate.opsForValue().get("USER_INFO") + "===========");
    }
}
