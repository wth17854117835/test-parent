package com.sitech.wth.practice.designPattern.您的设计模式.代理模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:43
 */
public class PanJinLian implements KindWomen{

    @Override
    public void makeEyesWithMan() {
        System.out.println("潘金莲抛媚眼");
    }

    @Override
    public void happyWithMan() {
        System.out.println("潘金莲在和男人做那个.....");
    }
}
