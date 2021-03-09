package com.sitech.wth.practice.stream;

import java.util.stream.Stream;

/**
 * Stream流中的常用方法 limit：用于截取Stream流中的元素
 * limit方法可以对流进行截取，只取用前n个。
 * Stream<T> limit(long maxSize);
 *      参数是一个long类型，如果集合当前长度大于参数则进行截取；否则不进行操作
 * limit是一个延迟方法。只是对流中的元素进行截取，返回的是一个新的流，可以继续调用Stream流中的其他方法
 **/
public class Demo08Stream_limit {

    public static void main(String[] args) {
        String[] arr = {"1","2","3","4"};
        Stream<String> stream = Stream.of(arr);
        Stream<String> limit = stream.limit(3);
        limit.forEach((i)->{
            System.out.println(i);
        });
    }
}
