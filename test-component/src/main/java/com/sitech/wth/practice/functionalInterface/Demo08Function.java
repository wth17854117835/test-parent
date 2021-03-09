package com.sitech.wth.practice.functionalInterface;

import java.util.function.Function;

/**
 * java.util.function.Function<T,R>接口根据一个类型的数据得到另一个类型的数据
 * 抽象方法：R apply(T t) 根据类型T的参数获取类型R的结果
 * 例如：将String转换为Integer
 * 默认方法：andThen
 **/
public class Demo08Function {

    public static void change(String str, Function<String, Integer> fun){
        Integer i = fun.apply(str);
        System.out.println(i);
    }

    public static void method(String str, Function<String, Integer> fun1, Function<Integer,String> fun2){
//        Integer i = fun1.apply(str) + 10;
//        String s2 = fun2.apply(i);

        String ss = fun1.andThen(fun2).apply(str);
        System.out.println(ss);
    }

    public static void main(String[] args) {
        //定义一个字符串
        String str = "100";

        change(str,(s)->{
            //把字符串类型的整数，转换为Integer类型的整数返回
            return Integer.parseInt(s);
        });

        //将String类型的100转换为Integer类型，把转换后的结果+10
        //把增加之后的结果转换为String类型
        method(str,
                (temp)->{
            //String转为Integer，+10
                    return Integer.parseInt(temp)+10;
                },
                (temp)->{
            //Integer转为String
                    return temp+"";
                });
    }

}
