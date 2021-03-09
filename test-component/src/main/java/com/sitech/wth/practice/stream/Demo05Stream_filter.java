package com.sitech.wth.practice.stream;

import java.util.stream.Stream;

/**
 * Stream流中的常用方法 filter：用于对Stream流中的数据进行过滤
 * Stream<T> filter(Predicate<? super T> predicate);
 * filter方法的参数Predicate是一个函数式接口，所以可以传递Lambda表达式，对数据进行过滤
 * Predicate中的抽象方法  boolean test(T t);
 **/
public class Demo05Stream_filter {
    public static void main(String[] args) {
        //创建一个Stream流
        Stream<String> stream = Stream.of("张三丰", "张三", "赵敏", "张无忌");
        //过滤只要姓张的人
        Stream<String> newStream = stream.filter((name) -> {
            return name.startsWith("张");
        });
        //遍历stream流
        newStream.forEach(name-> System.out.println(name));

        //Stream流是一个管道流，只能被消费（使用）一次
        //第一个Stream流调用完毕，数据就会流转到下一个Stream上
        //而这时第一个Stream流已经使用完毕，就会关闭了，所以第一个Stream流就不能调用方法了


        //============================
        Stream.of("张三丰", "张三", "赵敏", "张无忌")
                .filter((name)->name.startsWith("张"))
                .forEach(name-> System.out.println(name));

        //Stream流是一个管道流，只能被消费（使用）一次

    }
}
