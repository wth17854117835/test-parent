package com.sitech.wth.practice.mashibing.jvm.RunningData;

import com.sitech.wth.practice.mashibing.thread.T;

/**
 * @author: wangth_oup
 * @date: 2021-08-04 15:25
 * @description:
 **/
public class T02_MathTest {

    public static final int intData = 666;
    public static T t;

    public int compute111(){
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        T02_MathTest mathTest = new T02_MathTest();
        int compute = mathTest.compute111();
        System.out.println("======="+compute);
    }
}
