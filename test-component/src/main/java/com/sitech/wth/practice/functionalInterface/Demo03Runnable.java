package com.sitech.wth.practice.functionalInterface;

/**
 * java.lang.Runnable接口是一个函数式接口（只有一个抽象方法run）
 * 假设有一个startThread方法使用该接口作为参数，那么就可以使用Lambda进行传参。
 * 这种情况其实和Thread类的构造方法参数为Runnable没有本质区别
 **/
public class Demo03Runnable {
    //定义一个startThread()，方法的参数使用函数式接口Runnable;
    public static void startThread(Runnable runnable){
        //开启多线程
        new Thread(runnable).start();
    }

    public static void main(String[] args) {
        //1.调用startThread方法，方法的参数是一个接口，我们可以传递这个接口的匿名内部类
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---->线程启动了");
            }
        });

        //2.调用startThread方法，方法的参数是一个接口，我们可以传递Lambda表达式
        startThread(() -> {
            System.out.println(Thread.currentThread().getName()+"---->线程启动了");
        });

    }
}
