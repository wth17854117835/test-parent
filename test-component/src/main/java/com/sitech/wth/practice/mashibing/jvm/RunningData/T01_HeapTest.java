package com.sitech.wth.practice.mashibing.jvm.RunningData;


import java.util.ArrayList;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 18:08
 * @description: 测试堆溢出。设置参数 -Xms512m -Xmx512m
 **/
public class T01_HeapTest {
//    byte[] a = new byte[1024*100]; //100K
    byte[] a = new byte[1024*1024*100]; //100M


    public static void main(String[] args) throws InterruptedException {
        ArrayList<T01_HeapTest> heapTests = new ArrayList<>();
        int count = 0;
        while (true) {
            heapTests.add(new T01_HeapTest());
            count ++;
            Thread.sleep(10);
            System.out.println(count);
        }
    }

}
