package com.sitech.wth.practice.mashibing.juc;

import org.scalatest.concurrent.SleepHelper;

/**
 * @author: wangth_oup
 * @date: 2021-07-20 21:16
 * @description:
 **/
public class T01_HelloVolatile {

    private static /*volatile*/ boolean running = true;

    private static void m() {
        System.out.println("m start.......");
        while (running) {
//            System.out.println("hello");
        }
        System.out.println("m end........");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(T01_HelloVolatile::m, "t1");
        thread.start();

        SleepHelper.sleep(1L);
        //主线程休眠1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }

}
