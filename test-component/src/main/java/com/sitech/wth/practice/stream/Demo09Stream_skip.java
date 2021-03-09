package com.sitech.wth.practice.stream;

import java.util.stream.Stream;

/**
 * Stream流中的常用方法 skip：用于跳过Stream流中的元素
 * Stream<T> skip(long n);
 *      如果流当前长度大于n，则跳过前n个；否则将会的得到一个长度为0的空流
 **/
public class Demo09Stream_skip {
    public static void main(String[] args) {
        String[] arr = {"1","2","3","4","5"};
        Stream<String> stream = Stream.of(arr);
        Stream<String> skip = stream.skip(3);
        skip.forEach((i)->{
            System.out.println(i);
        });
    }
}
