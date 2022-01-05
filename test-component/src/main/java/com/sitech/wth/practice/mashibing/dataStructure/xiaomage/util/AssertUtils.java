package com.sitech.wth.practice.mashibing.dataStructure.xiaomage.util;

/**
 * @author: wangth_oup
 * @date: 2021-08-24 11:25
 * @description:
 **/
public class AssertUtils {
    public static void test(boolean value) {
        if(!value) try {
            throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
