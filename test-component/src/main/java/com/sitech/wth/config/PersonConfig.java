package com.sitech.wth.config;

import com.sitech.wth.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: wangth_oup
 * @date: 2020-08-05 17:44
 * @description: 测试@Scope注解设置的作用域
 **/
@Configuration
public class PersonConfig {

    @Scope("thread")
    @Bean("person")
    public Person person(){
        System.out.println("给容器中添加Person....");
        return new Person("wangth001", 18);
    }
}
