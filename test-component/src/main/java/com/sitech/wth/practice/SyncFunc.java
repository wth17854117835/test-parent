package com.sitech.wth.practice;

/**
 * @author: wangth_oup
 * @date: 2020-11-25 19:51
 * @description: 同步方法
 *
 * 非静态的同步方法是锁定类的实例的，而静态的同步方法是锁定类的；
 * 也就是说，对于非静态的同步方法，在同一时刻，一个类的一个实例中，只有一个线程能进入同步的方法。
 * 但是对于多个实例，每一个实例的一个线程都可以进入同一同步的方法。
 **/
public class SyncFunc {
    public synchronized void func1() {
        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is stop");
    }

    public static synchronized void func2() {
        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is stop");
    }

    public void methodB() {
        synchronized(this) {
            System.out.println("methodB.....");
        }
    }

    public void methodC() {
        String str = "sss";
        synchronized (str) {
            System.out.println("methodC.....");
        }
    }


    public static void main(String[] args) {
        NewThread newThread1 = new NewThread();
        NewThread newThread2 = new NewThread();
        NewThread newThread3 = new NewThread();

        newThread1.start();
        newThread2.start();
        newThread3.start();
    }

}

class NewThread extends Thread {

    //一个实例的多个线程，一次只能有一个线程进入非静态同步的方法。
//    private static SyncFunc syncFunc = new SyncFunc();

    //多个实例的线程能同时进入非静态同步的方法。
    private SyncFunc syncFunc = new SyncFunc();

    @Override
    public void run() {
//        syncFunc.func1();

        //多个实例的线程进入静态的同步方法
        syncFunc.func2();
        syncFunc.methodB();
        syncFunc.methodC();
    }

}
