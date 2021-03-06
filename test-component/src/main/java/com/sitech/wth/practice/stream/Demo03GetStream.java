package com.sitech.wth.practice.stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * java.util.stream.Stream<T>是java8新加入的最常用的流接口
 * 1.所有的Collection集合都可以通过stream默认方法获取流    default Stream<E> stream()
 * 2.Stream接口的静态方法of可以获取数组对应的流    static <T> Stream<T> of (T... values)
 *      参数是一个可变参数，那么就可以传递一个数组
 **/
public class Demo03GetStream {
    public static void main(String[] args) {
        //把集合转换为Stream流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //Set集合转换为Stream流
        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();

        //Map集合转换为Stream流
        Map<String,String> map = new HashMap<>();
        //获取键，存储到一个Set集合
        Set<String> key = map.keySet();
        Stream<String> stream3 = key.stream();
        //获取值，存储到一个Collection集合中
        Collection<String> values = map.values();
        Stream<String> stream4 = values.stream();
        //获取键值对(键与值的映射关系)EntrySet
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Stream<Map.Entry<String, String>> stream5 = entrySet.stream();

        //数组转换为Stream流
        Stream<Integer> stream6 = Stream.of(1, 2, 3, 4, 5, 6);
        //可变参数可以传递数组
        Integer[] arr1 = {1,2,3,4,5};
        Stream<Integer> stream7 = Stream.of(arr1);
        String[] arr2 = {"a","b","c"};
        Stream<String> stream8 = Stream.of(arr2);
    }
}
