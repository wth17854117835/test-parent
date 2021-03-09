package com.sitech.wth.practice.designPattern.simpleFactoryPattern;

/**
 * @author: wangth_oup
 * @date: 2020-11-26 17:39
 * @description: 抽象工厂模式
 * 步骤1： 创建抽象工厂类，定义具体工厂的公共接口；
 * 步骤2： 创建抽象产品族类 ，定义抽象产品的公共接口；
 * 步骤3： 创建抽象产品类 （继承抽象产品族类），定义具体产品的公共接口；
 * 步骤4： 创建具体产品类（继承抽象产品类） & 定义生产的具体产品；
 * 步骤5：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；
 * 步骤6：客户端通过实例化具体的工厂类，并调用其创建不同目标产品的方法创建不同具体产品类的实例
 *
 * 作者：Carson_Ho
 * 链接：https://www.jianshu.com/p/7deb64f902db
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/
//1.抽象工厂类
public abstract class ProductFactory03 {
    //两厢车 三厢车
    //2.0排量 2.4排量
    //抽象工厂模式相对于工厂方法模式：解决每个工厂只能创建一类产品的问题
    public abstract AbstractProduct ManufactureContainer();
    public abstract AbstractProduct ManufactureMould();
}
//2.抽象产品族类
abstract class AbstractProduct{
    public abstract void Show();
}
//3.抽象产品类
    //容器产品抽象类
abstract class ContainerProduct extends AbstractProduct{
    @Override
    public abstract void Show();
}
    //模具产品抽象类
abstract class MouldProduct extends AbstractProduct{
    @Override
    public abstract void Show();
}
//4.具体产品类
//容器产品A类
class ContainerProductA extends ContainerProduct{
    @Override
    public void Show() {
        System.out.println("生产出了容器产品A");
    }
}
//容器产品B类
class ContainerProductB extends ContainerProduct{
    @Override
    public void Show() {
        System.out.println("生产出了容器产品B");
    }
}
//模具产品A类
class MouldProductA extends MouldProduct{
    @Override
    public void Show() {
        System.out.println("生产出了模具产品A");
    }
}
//模具产品B类
class MouldProductB extends MouldProduct{
    @Override
    public void Show() {
        System.out.println("生产出了模具产品B");
    }
}
//具体工厂类
class Factory1 extends ProductFactory03{
    @Override
    public AbstractProduct ManufactureContainer() {
        return new ContainerProductA();
    }
    @Override
    public AbstractProduct ManufactureMould() {
        return new MouldProductA();
    }
}
class Factory2 extends ProductFactory03{
    @Override
    public AbstractProduct ManufactureContainer() {
        return new ContainerProductB();
    }
    @Override
    public AbstractProduct ManufactureMould() {
        return new MouldProductB();
    }
}

class FactoryPattern3 {
    public static void main(String[] args){
        ProductFactory03 mFactoryA = new Factory1();
        ProductFactory03 mFactoryB = new Factory2();
        //A厂当地客户需要容器产品A
        mFactoryA.ManufactureContainer().Show();
        //A厂当地客户需要模具产品A
        mFactoryA.ManufactureMould().Show();

        //B厂当地客户需要容器产品B
        mFactoryB.ManufactureContainer().Show();
        //B厂当地客户需要模具产品B
        mFactoryB.ManufactureMould().Show();
    }
}