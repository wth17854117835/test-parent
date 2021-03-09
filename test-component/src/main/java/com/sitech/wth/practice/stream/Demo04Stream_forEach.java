package com.sitech.wth.practice.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream流中的常用方法——forEach
 * void forEach(Consumer<? super T> action)
 * 该方法接受一个Consumer接口函数，会将每一个流元素交给该函数处理。
 * Consumer接口是一个消费型的函数式接口，可以传递lambda表达式，消费数据
 *
 * forEach方法用来遍历流中的数据
 * 是一个中结方法，遍历之后就不能继续调用Stream流中的其他方法
 **/
public class Demo04Stream_forEach {

    public static void main(String[] args) {
        //获取一个Stream流
        Stream<String> stream = Stream.of("张三", "李四", "王五", "赵六", "田七");

        //使用stream流中的的方法forEach对Stream流中的数据进行遍历
        stream.forEach((String name)->{
            System.out.println(name);
        });
//        stream.forEach(name-> System.out.println(name));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
//        list.parallelStream().forEach(System.out::println);
        list.parallelStream().forEach((s)->{
            System.out.println(Thread.currentThread().getName()+"==="+s);
        });

    }
}
