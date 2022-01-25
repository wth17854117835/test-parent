package com.sitech.wth.practice.mashibing.jvm.classloader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 10:06
 * @description:
 **/
public class Hello {

    public String m() {
        return "Hello,world!";
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 65; i < 91; i++) {
                    System.out.println("-------thread1--------"+(char) i);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.println("-------thread2--------"+ i);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

    }
}
