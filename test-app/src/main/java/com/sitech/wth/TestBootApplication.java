package com.sitech.wth;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 13:49
 * @description:
 **/
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class TestBootApplication {
    public static void main(String[] args){
        SpringApplication.run(TestBootApplication.class,args);
    }

    @Bean
    public Redisson redisson() {
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
