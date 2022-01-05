package com.sitech.wth.practice.mashibing.juc;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangth_oup
 * @date: 2021-12-09 17:35
 * @description: 使用CountDownLatch
 * 有3个人参见跑步比赛，需要先等指令员发指令枪后才能开跑，所有人都跑完之后，指令员喊一声，大家跑完了。
 **/
public class T00_Demo {

    public static class T extends Thread {
        //跑步耗时（秒）
        int runSeconds;
        CountDownLatch commanderCd;
        CountDownLatch countDownLatch;

        public T(int runSeconds, CountDownLatch commanderCd, CountDownLatch countDownLatch) {
            this.runSeconds = runSeconds;
            this.commanderCd = commanderCd;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            //等待指令员枪响
            try {
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread thread = Thread.currentThread();
            long start = System.currentTimeMillis();
            System.out.println(start + "," + thread.getName() + "开始跑！");
            try {
                //模拟耗时操作，休眠runCostSeconds秒
                TimeUnit.SECONDS.sleep(this.runSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

            long end = System.currentTimeMillis();
            System.out.println(end + "," + thread.getName() + "跑步结束,耗时：" + (end - start));
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start !");
        CountDownLatch commanderCD = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        long start = System.currentTimeMillis();
        T t1 = new T(2, commanderCD, countDownLatch);
        t1.setName("张三");
        t1.start();
        T t2 = new T(5, commanderCD, countDownLatch);
        t2.setName("李四");
        t2.start();
        T t3 = new T(10, commanderCD, countDownLatch);
        t3.setName("王五");
        t3.start();

        //主线程休眠5s
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + "，枪响了，大家开始跑！");
        commanderCD.countDown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "所有人跑完了，主线程耗时:" + (end - start));
    }
}
