package com.sitech.wth.practice.mashibing.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangth_oup
 * @date: 2022-01-21 16:01
 * @description: 多线程实现A到Z和1到26按顺序输出，比如1A2B3C4D......
 **/
public class T10_A1Z26 {

    //6种方法：
    //1.使用synchronized,wait,notify
    //2.使用volatile
    static volatile boolean thread2Run = false;
    //3.使用ReentrantLock的Condition
    //4.使用LockSupport
    static Thread t5 = null, t6 = null;
    //5.semaphore
    //6.SynchronousQueue


    public static void main(String[] args) throws InterruptedException {
        //使用synchronized,wait,notify
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            int i = 1;
            synchronized (lock) {
                while (i <= 26) {
                    System.out.print(i);
                    i++;
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();//必须，否则无法停止程序，因为最后肯定有个在wait
            }
        });
        Thread t2 = new Thread(() -> {
            char i = 'A';
            synchronized (lock) {
                while (i <= 'Z') {
                    System.out.print(i);
                    i++;
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();//必须，否则无法停止程序，因为最后肯定有个在wait
            }
        });
        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println("======");

        //使用volatile
        Thread thread1 = new Thread(() -> {
            int i = 1;
            while (i <= 26) {
                System.out.print(i);
                i++;
                //让thread2线程执行
                thread2Run = true;
                for (; ; ) {
                    if (!thread2Run) break;
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            char i = 'A';
            while (i <= 'Z') {
                while (thread2Run) {
                    System.out.print(i);
                    i++;
                    thread2Run = false;
                    break;
                }
            }
        });
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        System.out.println("======");

        //使用ReentrantLock的Condition
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition c1 = reentrantLock.newCondition();
        Condition c2 = reentrantLock.newCondition();
        Thread t3 = new Thread(() -> {
            reentrantLock.lock();
            try {
                int i = 1;
                while (i <= 26) {
                    System.out.print(i);
                    i++;
                    c2.signal();//唤醒c2队列中的线程
                    c1.await();//等待且释放线程当前的锁
                }
                c2.signal();    //唤醒线程2，必须，否则无法停止程序，因为最后肯定有个在wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });
        Thread t4 = new Thread(() -> {
            reentrantLock.lock();
            try {
                char i = 'A';
                while (i <= 'Z') {
                    System.out.print(i);
                    i++;
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });
        t3.start();
        t4.start();

        Thread.sleep(1000);
        System.out.println("======");

        //使用LockSupport
        t5 = new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                System.out.print(i);//起始先打印
                LockSupport.unpark(t6);//唤醒线程t6
                LockSupport.park();//自己阻塞，等待唤醒
            }
        });
        t6 = new Thread(() -> {
            for (int i = 65; i < 91; i++) {
                LockSupport.park();//起始先等待被唤醒
                System.out.print((char) i);
                LockSupport.unpark(t5);//唤醒线程t5
            }
        });
        t5.start();
        t6.start();

        Thread.sleep(1000);
        System.out.println("======");

        //使用semaphore
        final CountDownLatch latch = new CountDownLatch(2);
        Semaphore semaphore = new Semaphore(1, true);// 允许一个线程同时执行
        // semaphore只是控制信号量，每次只能被固定数量的线程拿到，不公平的话，被谁拿到不一定
        // 所以这里用锁控制两个线程都启动后再开始执行公平的争抢信号量
        Thread t7 = new Thread(() -> {
            latch.countDown();// 门闩 减一
            try {
                latch.await();// 等待门闩 为0 开始执行
                for (int i = 1; i <= 26; i++) {
                    semaphore.acquire(); // 获得许可 一共就1个许可
                    System.out.print(i);
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t8 = new Thread(() -> {
            latch.countDown();
            try {
                latch.await();
                for (int i = 65; i < 91; i++) {
                    semaphore.acquire();
                    System.out.print((char) i);
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t7.start();
        t8.start();

        Thread.sleep(1000);
        System.out.println("======");

        //使用SynchronousQueue
        SynchronousQueue<Integer> sq1 = new SynchronousQueue<>();
        SynchronousQueue<Integer> sq2 = new SynchronousQueue<>();
        Thread t9 = new Thread(() -> {
            try {
                for (int i = 1; i <= 26; i++) {
                    sq1.put(i);// put方法向队列中丢入一条数据，调用的时候产生了阻塞，直到take方法被调用时，put方法才从阻塞状态恢复正常。
                    System.out.print((char) sq2.take().intValue());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t10 = new Thread(() -> {
            try {
                for (int i = 65; i <= 91; i++) {
                    System.out.print(sq1.take());// 保证先打印1
                    sq2.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t9.start();
        t10.start();

    }

}
