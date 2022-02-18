package com.sitech.wth.practice.designPattern.您的设计模式.策略模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:33
 * 三个妙计是有了，那需要有个地方放这些妙计呀，放锦囊呀
 */
public class Context {

    //构造函数，你要使用那个妙计
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    //使用计谋了，看我出招了
    public void operate(){
        this.strategy.operate();
    }
}
