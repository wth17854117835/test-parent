package com.sitech.wth.practice.designPattern.您的设计模式.工厂方法模式;

/**
 * @author: wangth
 * @create: 2022-02-13 23:22
 */
public class NvWa {

    public static void main(String[] args) {
        //女娲第一次造人，试验性质，少造点，火候不足，缺陷产品
        System.out.println("------------造出的第一批人是这样的：白人-----------------");
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();

        //女娲第二次造人，火候加足点，然后又出了个次品，黑人
        System.out.println("------------造出的第二批人是这样的：黑人-----------------");
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();

        //第三批人了，这次火候掌握的正好，黄色人种（不写黄人，免得引起歧义），备注：RB人不属于此列
        System.out.println("------------造出的第三批人是这样的：黄色人种-----------------");
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.laugh();
        yellowHuman.talk();

    }
}
