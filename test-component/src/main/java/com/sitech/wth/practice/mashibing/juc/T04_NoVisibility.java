package com.sitech.wth.practice.mashibing.juc;

/**
 * @author: wangth_oup
 * @date: 2021-07-20 21:57
 * @description:
 **/
public class T04_NoVisibility {

    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new ReaderThread();
        number = 42;
        ready = true;
        t.join();
    }

}
