package com.sitech.wth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 13:49
 * @description:
 **/
@SpringBootApplication
@EnableScheduling
public class TestBootApplication {
    public static void main(String[] args){
        SpringApplication.run(TestBootApplication.class,args);
    }
}
