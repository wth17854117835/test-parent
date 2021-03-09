package com.sitech.wth.practice.designPattern;


/**
 * @author: wangth_oup
 * @date: 2020-11-25 17:08
 * @description: 饿汉模式
 **/
public class Demo01Singleton {

    // 将自身实例化对象设置为一个属性，并用static、final修饰
    private static final Demo01Singleton instance = new Demo01Singleton();

    // 构造方法私有化
    private Demo01Singleton() {}

    // 静态方法返回该实例
    public static Demo01Singleton getInstance() {
        return instance;
    }
}
