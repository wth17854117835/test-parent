package com.sitech.wth.practice.mashibing.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangth_oup
 * @date: 2022-01-21 15:33
 * @description: 使用公平锁 线程交替执行，不一定输出ABC ABC ABC
 **/
public class T09_ABCABC {

    private static ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    fairLock.lock();
                    System.out.print("A");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    fairLock.lock();
                    System.out.print("B");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }
        });
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    fairLock.lock();
                    System.out.print("C");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
