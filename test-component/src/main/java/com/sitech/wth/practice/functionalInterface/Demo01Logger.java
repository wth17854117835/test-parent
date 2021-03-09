package com.sitech.wth.practice.functionalInterface;

/**
 * 日志案例
 * 发现以下代码存在性能浪费，传递的第二个参数是一个拼接后的字符串
 * 先把字符串拼接好，然后再调用showLog方法
 * showLog方法中如果传递的日志级别不是1级
 * 那么就不会是拼接好的字符串，白拼接了，存在了浪费
 **/
public class Demo01Logger {
    //定义一个根据日志的级别，显示日志信息的方法
    public static void showLog(int level, String message){
        //对日志的级别进行判断，如果是1级别，那么输出日志信息
        if(level==1){
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        //定义三个日志信息
        String msg1 = "hello";
        String msg2 = "world";
        String msg3 = "java";
        //调用showLog方法，传递日志级别和日志信息
        showLog(1, msg1+msg2+msg3);
    }

}
