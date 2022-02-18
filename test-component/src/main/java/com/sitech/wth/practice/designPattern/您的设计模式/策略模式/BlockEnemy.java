package com.sitech.wth.practice.designPattern.您的设计模式.策略模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:32
 */
public class BlockEnemy implements IStrategy{

    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
