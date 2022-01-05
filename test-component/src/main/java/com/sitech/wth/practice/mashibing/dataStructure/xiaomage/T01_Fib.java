package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

import com.sitech.wth.practice.mashibing.dataStructure.xiaomage.util.TimeUtils;

import java.sql.Time;

/**
 * @author: wangth_oup
 * @date: 2021-08-23 10:59
 * @description:
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列，因数学家莱昂纳多·斐波那契（Leonardoda Fibonacci）
 * 以兔子繁殖为例子而引入，故又称为“兔子数列”，指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……在数学上，
 * 斐波那契数列以如下被以递推的方法定义：F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 **/
public class T01_Fib {

    /**
     * 效率问题
     * O(2^n)
     */
    public static int fib1(int n){
        if(n<=1) return n;
        return fib1(n-1)+fib1(n-2);
    }

    /**
     * 时间复杂度：O(n)
     */
    public static int fib2(int n){
        if(n<=1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /**
     * 省略一些空间
     */
    public static int fib3(int n){
        if(n<=1) return n;
        int first = 0;
        int second = 1;
        while (n-- > 1) {
//            second = first + second;
            second += first;
            first = second - first;
        }
        return second;
    }

    /**
     * 特征方程,时间复杂度O(1)
     */
    public static int fib4(int n){
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1+c)/2, n) - Math.pow((1-c)/2, n))/c);
    }

    public static void main(String[] args) {
//        System.out.println(fib1(64));
//        System.out.println(fib2(64));
        TimeUtils.check("fib1", () -> {
            System.out.println(fib1(46));
        });

//        TimeUtils.check("fib1", () -> System.out.println(fib1(46)));

        TimeUtils.check("fib2", new TimeUtils.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(46));
            }
        });
    }

}
