package com.sitech.wth.practice.mashibing.jvm.classloader;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 14:37
 * @description: class文件load到内存的执行过程
 **/
public class T05_ClassLoadingProcedure {

    public static void main(String[] args) {
        System.out.println(T.count);
    }
}

class T {
    //1.先count后T 结果为3
    //  Preparation阶段：静态成员变量赋默认值，count = 0 , t = null
    //  Initializing阶段：静态成员变量赋初始值，count = 2 , t 执行构造方法，执行count++  最终 ---> 3
    //2.先T后count 结果为2
    //  t = null , count = 0
    //  t = 执行构造方法，count++  --> 0++  --> 1 ; 然后给静态成员变量赋初始值 count = 2
    public static int count = 2;
    public static T t = new T();

    private T(){
        count++;
    }
}


