package com.sitech.wth.practice.designPattern.您的设计模式.单例模式;

/**
 * @author: wangth
 * @create: 2022-02-13 23:03
 * 通用单例模式（线程不安全）
 *
 * 单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。
 * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。
 * 这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象
 *
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 主要解决：一个全局使用的类频繁地创建与销毁。
 * 何时使用：当您想控制实例数目，节省系统资源的时候。
 * 如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
 * 关键代码：构造函数是私有的。
 */
@SuppressWarnings("all")
public class SingletonPattern {

    private static SingletonPattern singletonPattern;

    //限制住不能直接产生一个实例
    private SingletonPattern(){}

    public SingletonPattern getInstance(){

        //假如现在有两个线程 A 和线程 B，线程 A 执行到 this.singletonPattern = new SingletonPattern()，
        //正在申请内存分配，可能需要 0.001 微秒，就在这 0.001 微秒之内，线程 B 执
        //行到 if(this.singletonPattern == null)，你说这个时候这个判断条件是 true 还是 false？是 true，那
        //然后呢？线程 B 也往下走，于是乎就在内存中就有两个 SingletonPattern 的实例了
        if(singletonPattern == null){ //如果还没有实例，则创建一个
            singletonPattern = new SingletonPattern();
        }
        return singletonPattern;
    }
}
