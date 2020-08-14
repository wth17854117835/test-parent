package com.sitech.wth.service.impl;

import com.sitech.wth.util.RedisServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void testRedis() {
        redisServer.setStr("name", "wth");
        System.out.println("===========getName:" + redisServer.getKey("name") + "===========\n");
    }
}
