package com.sitech.wth.practice.functionalInterface;

import java.util.function.Consumer;

/**
 * 练习：将{"wangth,男","zhoucx,女","libo,男"}按照“姓名：xx。性别：xx”格式打印出来
 **/
public class Demo06ConsumerTest {

    public static void format(String[] arr, Consumer<String> con1, Consumer<String> con2){
        for (int i = 0; i < arr.length; i++) {
            con1.andThen(con2).accept(arr[i]);
        }
    }

    public static void main(String[] args) {
        //定义一个字符串数组
        String[] arr = {"wangth,男","zhoucx,女","libo,男"};

        format(arr,
                (message)->{
            //消费方式1：对message进行切割，获取姓名
                    String name = message.split(",")[0];
                    System.out.print("姓名："+name);
                },
                (message)->{
            //消费方式2：对message进行切割，获取性别
                    String sex = message.split(",")[1];
                    System.out.println("，性别："+sex+"。");
                });
    }
}
