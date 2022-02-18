package com.sitech.wth.practice.designPattern.您的设计模式.单例模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:59
 * 中国的历史上一般都是一个朝代一个皇帝，有两个皇帝的话，必然要PK出一个皇帝出来
 *
 * 单例模式很简单，就是在构造函数中多了加一个构造函数，访问权限是 private 的就可以了，这个模
 * 式是简单，但是简单中透着风险，风险？什么风险？在一个 B/S 项目中，每个 HTTP Request 请求到 J2EE
 * 的容器上后都创建了一个线程,每个线程都要创建同一个单例对象
 */
public class Emperor {

    private static Emperor emperor; //定义一个皇帝放在那里，然后给这个皇帝名字

    //世俗和道德约束你，目的就是不让你产生第二个皇帝
    private Emperor() {}

    public static Emperor getInstance() {
        if(emperor == null){ //如果皇帝还没有定义，那就定一个
            emperor = new Emperor();
        }
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
