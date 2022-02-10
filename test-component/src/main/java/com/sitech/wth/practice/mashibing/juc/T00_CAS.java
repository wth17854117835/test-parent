package com.sitech.wth.practice.mashibing.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: wangth_oup
 * @date: 2022-01-18 10:45
 * @description: 路人甲--使用JUC中的原子类（AtomicInteger）、Unsafe类、synchronized 实现计数器
 * LongAdder、LongAccumulator
 **/
public class T00_CAS {

    private static AtomicInteger count = new AtomicInteger();
    static int count1 = 0;

    private static Unsafe unsafe;
    private static int count2 = 0;
    //count2 在T00_CAS.class对象中的地址偏移量
    private static long countOffset;

    private static int count3 = 0;

    static LongAdder count4 = new LongAdder();
    static LongAccumulator count5 = new LongAccumulator((x,y) -> x + y, 0L);

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

    //1.使用AtomicInteger，内部调用 Unsafe 的 getAndAddInt()
    public static void request() throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep(5);
        count.incrementAndGet();
//        count.getAndIncrement();
    }

    //2.使用synchronized
    public static synchronized void request1() throws InterruptedException {
        //模拟耗时5毫秒
//        TimeUnit.MILLISECONDS.sleep(5);
        count1++;
    }

    //3.使用Unsafe类
    public static void request2() throws InterruptedException {
        //模拟耗时5毫秒
//        TimeUnit.MILLISECONDS.sleep(5);
        //对count原子加1
        unsafe.getAndAddInt(T00_CAS.class, countOffset, 1);
    }

    //4.使用Unsafe类的过期方法，monitorEnter monitorExit 必须成对出现，出现的次数必须一致，也就是说锁了n次，也必须释放n次，否则会造成死锁
    public static void request3() {
        unsafe.monitorEnter(T00_CAS.class);
        try {
            count3++;
        } finally {
            unsafe.monitorExit(T00_CAS.class);
        }
    }

    //5.使用LongAdder对象的increment()方法，reset()方法可以重置LongAdder的值，使其归0
    public static void request4() {
        count4.increment();
    }

    //6.使用LongAccumulator对象的accumulate(1)方法
    public static void request5() {
        count5.accumulate(1);
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        //使用线程池submit()，让100个线程处理10000个任务，每个任务对count+1操作
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        Future<AtomicInteger> result = null;
        for (int i = 0; i < 10000; i++) {
            result = executorService.submit(() -> {
                request();
                return count;
            });
            executorService.execute(() -> {
                try {
                    request1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            System.out.println(result.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        request(); //main，耗时：57,count=1000000
//                        request1(); //main，耗时：132,count1=1000000
//                        request2(); //main，耗时：66,count2=1000000
//                        request3(); //main，耗时：168,count3=1000000
//                        request4(); //main，耗时：53,count4=1000000
//                        request5(); //main，耗时：19,count5=1000000
                    }
                } catch (Exception e) {
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
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count1=" + count1);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count2=" + count2);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count3=" + count3);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count4=" + count4);
//        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count5=" + count5);
    }

}
