package com.sitech.wth.practice.functionalInterface;

/**
 * @author: wangth_oup
 * @date: 2020-10-29 11:33
 * @description: 函数式接口，有且只有一个抽象方法，接口中可以包含其他的方法（默认、静态、私有）
 * @FunctionalInterface注解作用：检测接口是否是一个函数式接口
 * 是：编异成功
 * 否：编译失败（接口中没有抽象方法，抽象方法的个数多余一个）
 **/
@FunctionalInterface
public interface MyFunctionalInterface {
    //定义一个抽象方法
    public abstract void show();

//    void show2();

}
