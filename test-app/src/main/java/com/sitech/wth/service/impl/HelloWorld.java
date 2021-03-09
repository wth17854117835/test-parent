package com.sitech.wth.service.impl;

import com.sitech.wth.aop.ControllerMethodLog;
import com.sitech.wth.service.inter.IHelloWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 14:40
 * @description:
 **/
//@Service("helloWorldSvc")
@RestController
@RequestMapping("/test/api/accept")
public class HelloWorld implements IHelloWorld {

    private Logger logger = LogManager.getLogger(HelloWorld.class);

    @PostMapping("/helloWorld")
    @Override
    @ControllerMethodLog
    public String hello() {
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
        return "hello";
    }

    public static void main(String[] args) {
        new Thread(()-> paralleTest2()).start();
//        new Thread(() -> paralleTest()).start();
    }

    static void paralleTest() {
        List<Integer> numbers = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29
        );
        final long begin = System.currentTimeMillis();
        numbers.parallelStream().map(k -> {
            try {
                Thread.sleep(1000);
                System.out.println((System.currentTimeMillis() - begin) + "ms => " + k + " \t" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return k;
        }).collect(Collectors.toList());
    }
    static void paralleTest2(){
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //并行流
        Arrays.asList(array).parallelStream().forEach(i -> {
            System.out.println(Thread.currentThread().getName() + " num:" + i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
