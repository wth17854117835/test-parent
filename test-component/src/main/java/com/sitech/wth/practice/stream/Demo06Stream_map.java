package com.sitech.wth.practice.stream;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Stream流中的常用方法 map：用于对Stream流中的数据进行过滤
 * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 * R apply(T t) 根据类型T的参数获取类型R的结果
 *
 * 该接口需要一个Function函数式接口参数，所以可以传递Lambda表达式，对数据进行过滤
 **/
public class Demo06Stream_map {
    public static void main(String[] args) {
        //获取string类型的Stream流
        Stream<String> stream1 = Stream.of("1", "2", "3", "4");
        //使用map方法，把字符串类型的整数，转换为Integer类型的整数
        Stream<Integer> stream2 = stream1.map((String s) -> {
            return Integer.parseInt(s);
        });
        //遍历
        stream2.forEach((i)->{
            System.out.println(i);
        });

//        stream1.map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.parseInt(s);
//            }
//        });
    }
}
