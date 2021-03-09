package com.sitech.wth.practice.functionalInterface;

import java.util.function.Consumer;

/**
 * java.util.function.Consumer<T>接口正好与Supplier接口相反，消费数据，根据泛型决定
 * Consumer接口是一个消费型接口，泛型指定什么类型就可以使用accept方法消费什么类型的数据，
 * 至于具体怎么消费（使用），需要自定义（输出、计算...）
 *
 * 默认方法andThen
 * 作用：需要两个Consumer接口，可以把两个Consumer接口组合到一起，再对数据进行消费
 **/
public class Demo06Consumer {

    public static void method(String name, Consumer<String> consumer){
        consumer.accept(name);
    }

    public static void method2(String name, Consumer<String> con1, Consumer<String> con2){
        //con1.accept(name);
        //con2.accept(name);
        //等价于
        con1.andThen(con2).accept(name);
    }

    public static void main(String[] args) {
        //调用method方法，传递字符串姓名，另一个参数是函数式接口，传递Lambda表达式
        method("wangth", (String name) -> {
            //对传递的字符串进行消费
            System.out.println(name);
        });

        //2.反转字符串
        method("zhoucx", (name) -> {
            String s = new StringBuffer(name).reverse().toString();
            System.out.println(s);
        });

        //3.匿名内部类方式
        method("lll", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //4.andThen
        method2("wangTH",
                (name)->{
                    //消费方式1：转大写
                    System.out.println(name.toUpperCase());
                },
                (name)->{
                    //消费方式2：转小写
                    System.out.println(name.toLowerCase());
                });
        //5.andThen 匿名内部类写法
        method2("ZhouCx", new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s.toUpperCase());
                    }
                },
                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s.toLowerCase());
                    }
                });

    }
}
