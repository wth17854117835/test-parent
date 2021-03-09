package com.sitech.wth.practice.functionalInterface;

import java.util.function.Supplier;

/**
 * 练习：求数组元素最大值
 *  使用Supplier接口作为方法参数类型，通过Lambda表达式求出int数组中的最大值。
 *  提示：接口泛型请用java.lang.Integer类
 **/
public class Demo05SupplierTest {
    //定义一个方法，用于获取int类型数组中元素的最大值，方法的参数传递Supplier接口，泛型使用Integer
    public static int getMax(Supplier<Integer> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        //定义一个int类型的数组
        int[] arr = {100,0,-50,888,99,33,-30};
        //调用getMax方法，参数是函数式接口，传递Lambda表达式
        int maxValue = getMax(() -> {
            //获取数组的最大值，并返回
            //定义一个变量，把数组中的第一个元素赋予该变量，记录数组中元素的最大值
            int max = arr[0];
            for (int i : arr) {
                //使用其他元素和最大值比较
                if (i > max) {
                    //如果i>max,替换max
                    max = i;
                }
            }
            return max;
        });
        System.out.println(maxValue);
    }
}
