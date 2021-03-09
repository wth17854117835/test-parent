package com.sitech.wth.practice.functionalInterface;

/**
 * @author: wangth_oup
 * @date: 2020-10-29 11:34
 * @description: 函数式接口的使用，一般可以作为方法的参数和返回值类型
 **/
public class Demo {
    //定义一个方法，参数使用的是函数式接口MyFunctionalInterface
    public static void test1(MyFunctionalInterface myFunctionalInterface){
        myFunctionalInterface.show();
    }

    public static void main(String[] args) {
        //1.调用test1方法，方法的参数是一个接口，所以可以传递接口的实现类对象
        test1(new MyFunctionalInterfaceImpl());

        //2.传递接口的匿名内部类
        test1(new MyFunctionalInterface() {
            @Override
            public void show() {
                System.out.println("使用匿名内部类重写接口中的抽象方法");
            }
        });

        //3.传递Lambda表达式
        test1(() -> {
            System.out.println("使用Lambda表达式重写接口中的抽象方法");
        });
        //3.1简化Lambda表达式
        test1(() -> System.out.println("简化"));
    }

}
