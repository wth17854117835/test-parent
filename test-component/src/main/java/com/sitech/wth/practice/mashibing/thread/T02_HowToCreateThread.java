package com.sitech.wth.practice.mashibing.thread;

import com.sitech.wth.practice.mashibing.juc.T01_HelloVolatile;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 21:13
 * @description:
 **/
public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    private static void m() {
        System.out.println("m start.......");
        System.out.println("m end........");
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(T02_HowToCreateThread::m, "t1").start();
        new Thread(() -> {
            System.out.println("Hello Lambda!");
        }).start();
    }

}
