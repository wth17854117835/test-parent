package com.sitech.wth.practice.designPattern.您的设计模式.工厂方法模式;

/**
 * @author: wangth
 * @create: 2022-02-13 23:20
 */
public class BlackHuman implements Human{

    @Override
    public void laugh() {
        System.out.println("黑人会笑");
    }

    @Override
    public void cry() {
        System.out.println("黑人会哭");

    }

    @Override
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
