package com.sitech.wth.practice.designPattern.simpleFactoryPattern;

/**
 * @author: wangth_oup
 * @date: 2020-11-26 15:40
 * @description: 简单工厂模式（静态工厂模式）
 **/
//工厂类
public class ProductFactory {
    public static SimpleProduct chooseProduct(String productName){
        //工厂类里用switch语句控制生产哪种商品；
        //使用者只需要调用工厂类的静态方法就可以实现产品类的实例化。
        switch (productName){
            case "1":
                return new Product1();
            case "2":
                return new Product2();
            case "3":
                return new Product3();
            default:
                return null;
        }
    }
}
//2.抽象产品类
abstract class SimpleProduct {
    public abstract void show();
}
//3.具体产品类
class Product1 extends SimpleProduct {
    @Override
    public void show() {
        System.out.println("生产出了产品1");
    }
}
class Product2 extends SimpleProduct {
    @Override
    public void show() {
        System.out.println("生产出了产品2");
    }
}
class Product3 extends SimpleProduct {
    @Override
    public void show() {
        System.out.println("生产出了产品3");
    }
}


class SimpleFactoryPattern {
    public static void main(String[] args) {
        ProductFactory myFactory = new ProductFactory();

        //客户要产品A
        try {
            //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            ProductFactory.chooseProduct("1").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品B
        try {
            myFactory.chooseProduct("2").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品C
        try {
            myFactory.chooseProduct("3").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品D
        try {
            myFactory.chooseProduct("4").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }
    }
}
