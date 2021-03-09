package com.sitech.wth.practice.functionalInterface;

import java.util.function.Predicate;

/**
 * java.util.function.Predicate<T>接口
 * 对某种数据进行判断，返回boolean
 * Predicate接口中包含一个抽象方法
 *  boolean test(T t): 用来对指定数据类型进行判断的方法
 *  默认方法：
 *  and
 *  or
 *
 **/
public class Demo07Predicate {

    public static boolean checkString(String str, Predicate<String> predicate){
        return predicate.test(str);
    }

    //&&:与运算符，有false则false
    public static boolean checkAnd(String str, Predicate<String> predicate1, Predicate<String> predicate2){
//        return predicate1.test(str) && predicate2.test(str);
        return predicate1.and(predicate2).test(str);
    }

    //||:或运算符，有true则true
    public static boolean checkOr(String str, Predicate<String> pre1, Predicate<String> pre2){
//        return pre1.test(str) || pre2.test(str);
        return pre1.or(pre2).test(str);
    }

    //!:非运算符(取反)，negate
    public static boolean checkNegate(String str, Predicate<String> pre){
//        return !pre.test(str);
        return pre.negate().test(str);
    }

    public static void main(String[] args) {
        //定义一个字符串
        String str = "AbCdeF";

        checkString(str, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>5;
            }
        });
        //调用checkString方法对字符串进行校验
        boolean b = checkString(str, (temp) -> {
            //对参数传递的字符串进行判断，判断长度是否大于5
            return temp.length() > 5;
        });
        System.out.println(b);

        //默认方法and：判断字符串长度是否大于5，判断字符串是否含有A
        boolean checkAnd = checkAnd(str,
                (temp) -> {
                    //判断字符串长度是否大于5
                    return temp.length() > 5;
                },
                (temp) -> {
                    //判断字符串是否含有A
                    return temp.contains("A");
                });
        System.out.println(checkAnd);

        //默认方法or：判断字符串长度是否大于50，判断字符串是否含有A
        boolean checkOr = checkOr(str,
                (temp) -> {
                    //判断字符串长度是否大于50
                    return temp.length() > 50;
                },
                (temp) -> {
                    //判断字符串是否含有A
                    return temp.contains("A");
                });
        System.out.println(checkOr);

        //默认方法negate
        boolean checkNegate = checkNegate(str, (temp) -> {
            return temp.length() > 5;
        });
        System.out.println(checkNegate);
    }
}
