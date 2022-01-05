package com.sitech.wth.practice.mashibing.thread;

/**
 * @author: wangth_oup
 * @date: 2021-07-25 21:22
 * @description:
 **/
public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        System.out.println(t.getState());

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getState());
    }
}
