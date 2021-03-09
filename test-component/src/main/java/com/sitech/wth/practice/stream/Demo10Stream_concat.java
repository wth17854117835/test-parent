package com.sitech.wth.practice.stream;

import java.util.stream.Stream;

/**
 * Stream流中的常用方法 concat：用于把流组合到一起
 * 如果有两个流，希望合并成一个流，可以使用Stream接口的静态方法concat
 * static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
 **/
public class Demo10Stream_concat {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("00", "01", "02", "03", "04");
        String[] arr = {"1","2","3","4","5"};
        Stream<String> stream2 = Stream.of(arr);

        //组合成一个流
        Stream.concat(stream1, stream2)
                .forEach((i)->{
                    System.out.println(i);
                });

    }
}
