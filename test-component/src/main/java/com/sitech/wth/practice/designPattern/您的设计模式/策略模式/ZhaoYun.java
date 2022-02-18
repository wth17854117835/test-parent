package com.sitech.wth.practice.designPattern.您的设计模式.策略模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:34
 * 策略模式的好处就是：体现了高内聚低耦合的特性
 * 还有一个就是扩展性，也就是 OCP 原则，策略类可以继续增加下去，只要修改 Context.java 就可以了
 */
public class ZhaoYun {

    /**
     * 赵云出场了，他根据诸葛亮给他的交代，依次拆开妙计
     */
    public static void main(String[] args) {
        Context context;
        //刚刚到吴国的时候拆第一个
        System.out.println("-----------刚刚到吴国的时候拆第一个-------------");
        context = new Context(new BackDoor()); //拿到妙计
        context.operate(); //拆开执行
        System.out.println("========================");

        //刘备乐不思蜀了，拆第二个了
        System.out.println("-----------刘备乐不思蜀了，拆第二个了-------------");
        context = new Context(new GivenGreenLight());
        context.operate(); //执行了第二个锦囊了
        System.out.println("========================");

        //孙权的小兵追了，咋办？拆第三个
        System.out.println("-----------孙权的小兵追了，咋办？拆第三个-------------");
        context = new Context(new BlockEnemy());
        context.operate(); //孙夫人退兵
        System.out.println("========================");
    }
}
