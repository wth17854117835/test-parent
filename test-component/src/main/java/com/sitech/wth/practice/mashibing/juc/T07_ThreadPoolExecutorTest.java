package com.sitech.wth.practice.mashibing.juc;

import org.scalatest.concurrent.SleepHelper;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 23:13
 * @description: ThreadPoolExecutor
 **/
public class T07_ThreadPoolExecutorTest {
    public volatile static int endNum = -1;
    private static final AtomicInteger threadNumber = new AtomicInteger(1);

    private static class Task implements Runnable {

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "Task" + i);
            while (endNum != i) {
                SleepHelper.sleep(1L);
            }
            System.out.println("Task" + i + "结束！");
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        //自定义线程池
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        //自定义线程池（线程工厂、拒绝策略）
        ThreadPoolExecutor tpe1 = new ThreadPoolExecutor(1,1,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("自定义线程--" + threadNumber.getAndIncrement() + "--");
                    return thread;
                },
                (runnable,executors) -> {
                    System.out.println("无法处理的任务：" + runnable.toString());
                });

        Task t1 = new Task(1);
        tpe.execute(t1);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());

        System.out.println("==========================");

        Task t2 = new Task(2);
        tpe.execute(t2);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());

        System.out.println("==========================");

        Task t3 = new Task(3);
        tpe.execute(t3);
        Task t4 = new Task(4);
        tpe.execute(t4);
        Task t5 = new Task(5);
        tpe.execute(t5);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
    }

}
