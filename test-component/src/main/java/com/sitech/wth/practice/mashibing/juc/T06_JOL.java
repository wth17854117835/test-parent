package com.sitech.wth.practice.mashibing.juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: wangth_oup
 * @date: 2021-07-21 22:02
 * @description: java对象在内存中的布局
 **/
public class T06_JOL {

    public static void main(String[] args) {

        Object o = new Object();
        //new 出来的状态
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

//        System.out.println(o.hashCode());
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) { //上锁，标记的是 markword 区
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        //锁释放的状态
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
