package com.sitech.wth.practice.mashibing.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 23:14
 * @description: 用三个线程，按顺序输出ABC ABC ABC
 **/
public class T08_ABCABC {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();

    //门栓
    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("A");
                    cB.signal();
                    //门栓，把latchB的门栓放开
                    if(i == 0) latchB.countDown();
                    //自己进入cA队列
                    cA.await();
                }
                //如果没有最后一次叫醒，程序不会结束
                cB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-A");

        Thread threadB = new Thread(() -> {
            //先等待唤醒
            try {
                //门栓卡在这，不能往下运行
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("B");
                    cC.signal();
                    if(i == 0) latchC.countDown();
                    cB.await();
                }
                cC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-B");

        Thread threadC = new Thread(() -> {
            //先等待唤醒
            try {
                latchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("C");
                    cA.signal();
                    cC.await();
                }
                cA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

}
