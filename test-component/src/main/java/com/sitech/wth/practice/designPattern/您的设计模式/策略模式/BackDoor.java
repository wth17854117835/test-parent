package com.sitech.wth.practice.designPattern.您的设计模式.策略模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:31
 */
public class BackDoor implements IStrategy{

    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
