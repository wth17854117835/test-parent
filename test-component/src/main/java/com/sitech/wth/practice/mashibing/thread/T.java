package com.sitech.wth.practice.mashibing.thread;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 21:58
 * @description:
 **/
public class T implements Runnable {

    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
//            T t = new T(); // t对象不一样，锁不一样
            new Thread(t, "THREAD" + i).start();
        }
    }
}
