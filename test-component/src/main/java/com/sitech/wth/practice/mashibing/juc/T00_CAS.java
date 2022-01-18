package com.sitech.wth.practice.mashibing.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wangth_oup
 * @date: 2022-01-18 10:45
 * @description: 路人甲--使用JUC中的原子类（AtomicInteger）、Unsafe类、synchronized 实现计数器
 **/
public class T00_CAS {

    private static AtomicInteger count = new AtomicInteger();
    static int count1 = 0;

    private static Unsafe unsafe;
    private static int count2 = 0;
    //count2 在T00_CAS.class对象中的地址偏移量
    private static long countOffset;

    private static int count3 = 0;

    static {
        try {
            //使用反射获取Unsafe对象，或者直接 private static final Unsafe unsafe = Unsafe.getUnsafe();
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            //获取count2字段在T00_CAS中的内存地址的偏移量
            Field count2Field = T00_CAS.class.getDeclaredField("count2");
            countOffset = unsafe.staticFieldOffset(count2Field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用AtomicInteger
    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count.incrementAndGet();
//        count.getAndIncrement();
    }

    //使用synchronized
    public static synchronized void request1() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        count1++;
    }

    //使用Unsafe类
    public static void request2() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        //对count原子加1
        unsafe.getAndAddInt(T00_CAS.class, countOffset, 1);
    }

    //使用Unsafe类的过期方法，monitorEnter monitorExit 必须成对出现，出现的次数必须一致，也就是说锁了n次，也必须释放n次，否则会造成死锁
    public static void request3() {
        unsafe.monitorEnter(T00_CAS.class);
        try {
            count3++;
        } finally {
            unsafe.monitorExit(T00_CAS.class);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request(); //main，耗时：238,count=1000
//                        request1(); //main，耗时：7138,count1=1000
//                        request2(); //main，耗时：182,count2=1000
//                        request3(); //main，耗时：253,count2=1000
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count1=" + count1);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count2=" + count2);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count3=" + count3);
    }

}
