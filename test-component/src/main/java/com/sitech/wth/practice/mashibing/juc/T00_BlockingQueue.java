package com.sitech.wth.practice.mashibing.juc;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @author: wangth_oup
 * @date: 2022-01-18 16:35
 * @description: JUC中的阻塞队列 Queue、BlockingQueue接口
 **/
public class T00_BlockingQueue {
    //推送队列
    static ArrayBlockingQueue<Msg> arrayPushQueue = new ArrayBlockingQueue<Msg>(1000);
    static LinkedBlockingQueue<String> LinkedPushQueue = new LinkedBlockingQueue<String>();//默认大小为 Integer.MAX_VALUE
    static PriorityBlockingQueue<Msg> priorityPushQueue = new PriorityBlockingQueue<Msg>();//默认大小为11
    static SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
    static DelayQueue<Msg2> delayQueue = new DelayQueue<Msg2>();

    static {
        new Thread(() -> {
            while (true) {
                Msg msg;
                Msg2 msg2;
                try {
                    long startTime = System.currentTimeMillis();
                    //获取一条推送消息，此方法会进行阻塞，直到返回结果
//                    msg = arrayPushQueue.take();
//                    msg = priorityPushQueue.take();
                    msg2 = delayQueue.take();
                    long endTime = System.currentTimeMillis();
                    //模拟推送耗时
                    TimeUnit.MILLISECONDS.sleep(500);
//                    System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息:%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName(), msg));
                    System.out.println(String.format("定时发送时间：%s,实际发送时间：%s,发送消息:%s", msg2.sendTimeMs, endTime, msg2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //推送信息封装（用于ArrayBlockingQueue、PriorityBlockingQueue）
    static class Msg implements Comparable<Msg> {
        //优先级，越小优先级越高
        private int priority;
        //推送的信息
        private String msg;

        public Msg(int priority, String msg) {
            this.priority = priority;
            this.msg = msg;
        }

        @Override
        public int compareTo(Msg o) {
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    //推送信息封装（用于延时队列）
    static class Msg2 implements Delayed {
        //优先级，越小优先级越高
        private int priority;
        //推送的信息
        private String msg;
        //定时发送时间，毫秒格式
        private long sendTimeMs;

        public Msg2(int priority, String msg, long sendTimeMs) {
            this.priority = priority;
            this.msg = msg;
            this.sendTimeMs = sendTimeMs;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.sendTimeMs - Calendar.getInstance().getTimeInMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof Msg2) {
                Msg2 c2 = (Msg2) o;
                return Integer.compare(this.priority, c2.priority);
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Msg2{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    ", sendTimeMs=" + sendTimeMs +
                    '}';
        }
    }

    //推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
//    public static void pushMsg(String msg) throws InterruptedException {
//        arrayPushQueue.put(msg);
//    }

    public static void pushMsg(int priority, String msg) throws InterruptedException {
        arrayPushQueue.put(new Msg(priority, msg));
//        priorityPushQueue.put(new Msg(priority, msg));
    }

    public static void pushMsg(int priority, String msg, long sendTimeMs) throws InterruptedException {
        delayQueue.put(new Msg2(priority, msg, sendTimeMs));
    }

    public static void main(String[] args) throws InterruptedException {
        //1.测试ArrayBlockingQueue队列
//        for (int i = 1; i <= 5; i++) {
//            String msg = "一起来学java高并发,第" + i + "天";
//            //模拟耗时
//            TimeUnit.SECONDS.sleep(i);
//            T00_BlockingQueue.pushMsg(i, msg);
//        }

        //2.测试PriorityBlockingQueue优先级队列
//        for (int i = 5; i >= 1; i--) {
//            String msg = "一起来学java高并发,第" + i + "天";
//            T00_BlockingQueue.pushMsg(i, msg);
//        }

        //3.测试synchronousQueue队列，没有容量
        //main方法中启动了一个线程，调用synchronousQueue.put方法向队列中丢入一条数据，调用的时候产生了阻塞，直到take方法被调用时，put方法才从阻塞状态恢复正常。
//        new Thread(() -> {
//            try {
//                long starTime = System.currentTimeMillis();
//                synchronousQueue.put("java高并发系列，路人甲Java!");
//                long endTime = System.currentTimeMillis();
//                System.out.println(String.format("[%s,%s,take耗时:%s],%s", starTime, endTime, (endTime - starTime), Thread.currentThread().getName()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        //休眠5秒之后，从队列中take一个元素
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(System.currentTimeMillis() + "调用take获取并移除元素," + synchronousQueue.take());

        //4.测试DelayQueue队列，延时发送
        for (int i = 5; i >= 1; i--) {
            String msg = "一起来学java高并发,第" + i + "天";
            T00_BlockingQueue.pushMsg(i, msg, Calendar.getInstance().getTimeInMillis() + i * 2000);
        }


    }

}
