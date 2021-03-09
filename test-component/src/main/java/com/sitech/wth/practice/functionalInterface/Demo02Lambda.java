package com.sitech.wth.practice.functionalInterface;

/**
 * 使用Lambda优化日志案例
 * Lambda特点：延迟加载
 * Lambda使用前提：必须存在函数式接口
 **/
public class Demo02Lambda {
    //定义一个显示日志的方法，方法的参数传递日志的等级和接口
    public static void showLog(int level, MessageBuilder mb){
        //对日志的等级进行判断，如果是1级，则调用MessageBuilder接口中的拼接字符串方法buildMessage
        if(level==1){
            System.out.println(mb.buildMessage());
        }
    }

    public static void main(String[] args) {
        //定义三个日志信息
        String msg1 = "hello";
        String msg2 = "world";
        String msg3 = "java";

        //调用showLog方法，参数是Lambda表达式
        //使用Lambda表达式作为参数传递，仅仅是把参数传递到showLog方法中，只有满足条件日志的等级为1，
        // 才会调用接口MessageBuilder中的方法buildMessage，才会进行字符串拼接
        //如果条件不满足，接口中的方法不会执行，拼接字符串的代码也不会执行。
        showLog(1, () ->{
            //返回一个拼接好的字符串
            return msg1+msg2+msg3;
        });
        showLog(2, () ->{
            System.out.println("不满足条件不执行");
            return msg1+msg2+msg3;
        });
    }
}
