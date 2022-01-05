package com.sitech.wth.practice.mashibing.jvm.classloader;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 11:31
 * @description:
 **/
public class T00_ClassLoaderLevel {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());

        System.out.println(T00_ClassLoaderLevel.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

}
