package com.sitech.wth.practice.mashibing.juc;

import java.io.IOException;

/**
 * @author: wangth_oup
 * @date: 2021-07-20 22:04
 * @description: This逸出
 **/
public class T05_ThisEscape {

    private int num = 8;

    /**
     * 理论上有可能打印出来0
     */
    public T05_ThisEscape() {
        new Thread(() -> System.out.println(this.num)).start();
        //start分开写，保证new完对象后调用
        //new 完对象， num = 0
        //调用构造方法后， num = 8
    }

    public static void main(String[] args) throws Exception {
        new T05_ThisEscape();
        System.in.read();
    }
}
