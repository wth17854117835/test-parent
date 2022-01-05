package com.sitech.wth.practice.mashibing.threadLocal;

import com.sitech.wth.bean.Person;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 15:26
 * @description: 线程本地变量
 **/
public class T01_ThreadLocal {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + "---" + tl.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            System.out.println(Thread.currentThread() + "---" + tl.get());
        }).start();

    }

}
