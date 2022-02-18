package com.sitech.wth.practice.designPattern.您的设计模式.模板方法模式;

/**
 * @author: wangth
 * @create: 2022-02-14 11:48
 *
 * 在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。
 * 它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
 *
 * 优点：1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。
 * 缺点：每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大。
 */
public class Client {

    public static void main(String[] args) {

        HummerModel h = new HummerH1Model();
        h.run();

        h = new HummerH2Model();
        h.run();
    }
}
