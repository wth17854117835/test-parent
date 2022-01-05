package com.sitech.wth.practice.mashibing.juc;

import org.scalatest.concurrent.SleepHelper;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 23:13
 * @description: ThreadPoolExecutor
 **/
public class T07_ThreadPoolExecutorTest {
    public volatile static int endNum = -1;

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
    }

    public static void main(String[] args) {
        //自定义线程池
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

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
