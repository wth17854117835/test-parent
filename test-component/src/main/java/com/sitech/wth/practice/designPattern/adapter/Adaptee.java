package com.sitech.wth.practice.designPattern.adapter;

/**
 * @author: wangth_oup
 * @date: 2020-12-03 11:37
 * @description: 适配器模式
 * 将一个类的接口转换成客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作。
 *
 * 代理模式和适配器模式最大的区别，代理模式是与原对象实现同一个接口，而适配器类则是匹配新接口，说白了，实现一个新的接口。
 **/

// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
public class Adaptee {
    public void specificRequest() {
        System.out.println("被适配类具有 特殊功能...");
    }
}

// 目标接口，或称为标准接口
interface Target {
    public abstract void request();
}

// 具体目标类，只提供普通功能
class ConcreteTarget implements Target {
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}

// 方法一：适配器类，继承了被适配类，同时实现标准接口
class Adapter extends Adaptee implements Target{
    public void request() {
        super.specificRequest();
    }
}

// 方法二：适配器类，直接关联被适配类，同时实现标准接口
class Adapter2 implements Target{
    // 直接关联被适配类
    private Adaptee adaptee;

    // 可以通过构造函数传入具体需要适配的被适配类对象
    public Adapter2 (Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 这里是使用委托的方式完成特殊功能
        adaptee.specificRequest();
    }
}

class testAdapter {
    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类
        Target adapter = new Adapter();
        adapter.request();

        Target adapter2 = new Adapter2(new Adaptee());
        adapter2.request();
    }
}
