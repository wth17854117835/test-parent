package com.sitech.wth.practice.designPattern.您的设计模式.工厂方法模式;

/**
 * @author: wangth
 * @create: 2022-02-13 23:19
 * 定义一个人类的统称
 */
public interface Human {

    //人是愉快的，会笑的，本来是想用smile表示，想了一下laugh更合适，好长时间没有大笑了；
    public void laugh();

    //人类还会哭，代表痛苦
    public void cry();

    //人类会说话
    public void talk();
}
