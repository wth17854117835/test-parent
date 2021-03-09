package com.sitech.wth.practice.functionalInterface;

import java.util.function.Supplier;

/**
 * 常用的函数式接口
 * java.util.function.Supplier<T>接口仅包含一个无参的方法: T get()。用来获取一个泛型参数指定类型的对象数据。
 * Supplier<T>接口被称为生产型接口，指定接口的泛型是什么类型，那么接口中的get方法就会生产什么类型的数据
 **/
public class Demo05Supplier {

    //定义一个方法，方法的参数传递Supplier<T>接口，泛型为String，就会返回一个String
    public static String getString(Supplier<String> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        //调用getString方法，方法的参数Supplier是一个函数式接口与，可以传递Lambda表达式
        String s = getString(() -> {
            return "wangth";
        });
        System.out.println(s);

        //优化Lambda表达式
        String s2 = getString(() -> "wangth_oup");
        System.out.println(s2);

        //匿名内部类方式
        getString(new Supplier<String>() {
            @Override
            public String get() {
                return "sss";
            }
        });
    }
}
