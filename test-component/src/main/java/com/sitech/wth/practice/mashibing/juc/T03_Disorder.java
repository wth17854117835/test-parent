package com.sitech.wth.practice.mashibing.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author: wangth_oup
 * @date: 2021-07-20 21:50
 * @description: 有序性
 **/
public class T03_Disorder {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws Exception {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            x = 0; y = 0; a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(2);

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
                latch.countDown();
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                    latch.countDown();
                }
            });

            t1.start();
            t2.start();
            latch.await();
            String result = "第" + i + "次(" + x + "," + y + ")";
            if(x == 0 && y == 0){
                System.err.println(result);
            }
        }
    }
}
