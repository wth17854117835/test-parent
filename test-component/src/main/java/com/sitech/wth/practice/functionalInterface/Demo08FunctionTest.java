package com.sitech.wth.practice.functionalInterface;

import java.util.function.Function;

/**
 * 练习：自定义函数模型拼接
 * String s = "wangth,23";
 * 1.将字符串截取年龄部分，得到字符串 Function<String, String> "wangth,23" -> "23"
 * 2.将上一步的字符串转换为int类型的数字 Function<String, Integer> "23" -> 23
 * 3.将上一步的int数字累加100，得到结果int数字 Function<Integer, Integer> 23 -> 123
 **/
public class Demo08FunctionTest {

    public static int method(String str,
                              Function<String,String> fun1,
                              Function<String,Integer> fun2,
                              Function<Integer,Integer> fun3){
        return fun1.andThen(fun2).andThen(fun3).apply(str);
    }

    public static void main(String[] args) {
        //定义一个字符串
        String s = "wangth,23";

        int i = method(s,
                (temp) -> {
                    //截取字符串
                    return temp.split(",")[1];
                },
                (temp) -> {
                    //转为Integer类型
                    return Integer.parseInt(temp);
                },
                (temp) -> {
                    //累加100
                    return temp + 100;
                });
        System.out.println(i);
    }
}
