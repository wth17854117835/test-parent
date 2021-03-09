package com.sitech.wth.practice.designPattern.simpleFactoryPattern;

/**
 * @author: wangth_oup
 * @date: 2020-11-26 17:27
 * @description: 工厂方法模式
 * 步骤1： 创建抽象工厂类，定义具体工厂的公共接口；
 * 步骤2： 创建抽象产品类 ，定义具体产品的公共接口；
 * 步骤3： 创建具体产品类（继承抽象产品类） & 定义生产的具体产品；
 * 步骤4：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；
 * 步骤5：外界通过调用具体工厂类的方法，从而创建不同具体产品类的实例
 **/

//1.抽象工厂类
public abstract class ProductFactory02 {
    public abstract Product Manufacture();
}
//2.抽象产品类
abstract class Product {
    public abstract void show();
}
//3.具体产品类
class ProductA extends Product {
    @Override
    public void show() {
        System.out.println("生产出了产品A");
    }
}
class ProductB extends Product {
    @Override
    public void show() {
        System.out.println("生产出了产品B");
    }
}
class ProductC extends Product {
    @Override
    public void show() {
        System.out.println("生产出了产品C");
    }
}
//4.具体工厂类
class FactoryA extends ProductFactory02{
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}
class FactoryB extends ProductFactory02{
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}


class FactoryPattern {
    public static void main(String[] args){
        //客户要产品A
        ProductFactory02 mFactoryA = new FactoryA();
        mFactoryA.Manufacture().show();

        //客户要产品B
        ProductFactory02 mFactoryB = new FactoryB();
        mFactoryB.Manufacture().show();
    }
}