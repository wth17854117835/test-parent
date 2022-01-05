package com.sitech.wth.practice.mashibing.jvm.classloader;

/**
 * @author: wangth_oup
 * @date: 2021-07-26 17:36
 * @description:
 **/
public class T02_LoadClassByHand {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = T02_LoadClassByHand.class.getClassLoader().loadClass("com.sitech.wth.practice.mashibing.jvm.classloader.T01_ClassLoaderScope");
        System.out.println(clazz.getName());

        //利用类加载器加载资源
//        T02_LoadClassByHand.class.getClassLoader().getResourceAsStream("");
    }

}
