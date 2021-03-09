package com.sitech.wth.practice.designPattern;


import groovy.lang.Singleton;

/**
 * @author: wangth_oup
 * @date: 2020-11-25 17:08
 * @description: 懒汉模式
 **/
public class Demo02Singleton {

    // 将自身实例化对象设置为一个属性，并用static修饰
    private static volatile Demo02Singleton instance;

    // 构造方法私有化
    private Demo02Singleton() {}

    // 静态方法返回该实例
//    public static Demo02Singleton getInstance() {
//        try {
//            if(instance == null) {
//                Thread.sleep(1000);
//                instance = new Demo02Singleton();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }

    //同步方法
//    public static synchronized Demo02Singleton getInstance(){
//        try {
//            if(instance == null) {
//                Thread.sleep(1000);
//                instance = new Demo02Singleton();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }

    //同步代码块
    public static Demo02Singleton getInstance(){
        if(instance == null) {
            synchronized (Demo02Singleton.class){
                if (instance == null) {
                    instance = new Demo02Singleton();
                }
            }
        }
        return instance;
    }
}
