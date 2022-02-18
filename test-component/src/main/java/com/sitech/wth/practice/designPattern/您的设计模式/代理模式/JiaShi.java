package com.sitech.wth.practice.designPattern.您的设计模式.代理模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:46
 */
public class JiaShi implements KindWomen{

    @Override
    public void makeEyesWithMan() {
        System.out.println("贾氏抛媚眼");
    }

    @Override
    public void happyWithMan() {
        System.out.println("贾氏正在Happy中......");
    }
}
