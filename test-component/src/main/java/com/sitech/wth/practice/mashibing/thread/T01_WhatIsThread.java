package com.sitech.wth.practice.mashibing.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 21:10
 * @description:
 **/
public class T01_WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
//        new T1().run();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

}
