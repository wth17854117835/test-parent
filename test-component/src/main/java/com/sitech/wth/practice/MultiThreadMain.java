package com.sitech.wth.practice;

import com.sitech.wth.practice.designPattern.Demo01Singleton;
import com.sitech.wth.practice.designPattern.Demo02Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: wangth_oup
 * @date: 2020-11-25 17:11
 * @description: 多线程
 **/
public class MultiThreadMain {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i< 20; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+ Demo02Singleton.getInstance());
                }
            });
        }
        threadPool.shutdown();
    }
}
