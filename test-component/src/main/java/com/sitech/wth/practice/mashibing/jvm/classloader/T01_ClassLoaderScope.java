package com.sitech.wth.practice.mashibing.jvm.classloader;

/**
 * @author: wangth_oup
 * @date: 2021-07-26 17:26
 * @description:
 **/
public class T01_ClassLoaderScope {

    public static void main(String[] args) {
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));

        System.out.println("=======================");

        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));

        System.out.println("=======================");

        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }

}
