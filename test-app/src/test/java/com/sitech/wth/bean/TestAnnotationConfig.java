package com.sitech.wth.bean;

import com.sitech.wth.config.PersonConfig;
import com.sitech.wth.util.ThreadScope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2020-08-05 17:51
 * @description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAnnotationConfig {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);
    //向容器中注册自定义的scope
//    context.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());


    private String test1(){
        //使用容器获取bean
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + context.getBean("person"));
                System.out.println(Thread.currentThread() + "," + context.getBean("person"));
            }).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
