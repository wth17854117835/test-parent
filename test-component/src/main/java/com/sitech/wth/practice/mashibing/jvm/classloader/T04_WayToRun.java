package com.sitech.wth.practice.mashibing.jvm.classloader;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 10:39
 * @description: 执行模式：混合模式（默认）、解释模式（-Xint）、编译模式（-Xcomp）
 **/
public class T04_WayToRun {

    public static void main(String[] args) {
        //第一次执行是为了告诉jvm进行优化，将热点代码编译到本地
        for (int i = 0; i < 10_0000; i++) {
            m();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void m(){
        for (long i = 0; i < 10_0000L; i++) {
            long j = i%3;
        }
    }

}
