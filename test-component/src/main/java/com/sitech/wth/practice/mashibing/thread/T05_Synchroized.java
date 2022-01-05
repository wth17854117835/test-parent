package com.sitech.wth.practice.mashibing.thread;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 21:50
 * @description:
 **/
public class T05_Synchroized {

    private int count = 10;
    private Object o = new Object();

    public /*synchronized*/ void m() {
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

        synchronized (this) {
            count-- ;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

        synchronized (T05_Synchroized.class) {
            count-- ;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
