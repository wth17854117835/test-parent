package com.sitech.wth.practice.designPattern.您的设计模式.策略模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:32
 */
public class GivenGreenLight implements IStrategy{

    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行！");
    }
}
