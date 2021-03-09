package com.sitech.wth.practice.functionalInterface;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * 练习：集合信息筛选
 * String[] arr = {"wangth,男","zhoucx,女","libo,男","libb,女"}
 * 筛选出: 1.4个字 2.男
 * 分析：
 *  两个判断条件，使用两个Predicate
 *  同时满足条件，使用默认方法and
 **/
public class Demo07PredicateTest {

    public static ArrayList<String> filter(String[] arr, Predicate<String> pre1, Predicate<String> pre2){
        //定义一个ArrayList，存储过滤之后的信息
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            boolean b = pre1.and(pre2).test(arr[i]);
            if(b){
                arrayList.add(arr[i]);
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        //定义一个字符串数组
        String[] arr = {"wangth,男","zhoucx,女","libo,男","libb,女"};

        ArrayList<String> filterList = filter(arr,
                (temp) -> {
                    //筛选出4个字
                    String name = temp.split(",")[0];
                    return name.length() == 4;
                },
                (temp) -> {
                    //筛选男
                    String sex = temp.split(",")[1];
                    return sex.equals("男");
                }
        );
        //输出
        for (String s : filterList) {
            System.out.println(s);
        }
        filterList.forEach((s)->{System.out.println(s);});
        filterList.forEach((s)->System.out.println(s));
        filterList.forEach(System.out::println);
    }

}
