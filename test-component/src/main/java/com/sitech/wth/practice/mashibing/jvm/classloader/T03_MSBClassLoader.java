package com.sitech.wth.practice.mashibing.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author: wangth_oup
 * @date: 2021-07-27 9:49
 * @description: 自定义类加载器，继承ClassLoader，实现findClass()  模板方法设计模式
 **/
public class T03_MSBClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        File f = new File("E:/wangttthhh/",name.replaceAll(".", "/").concat(".class"));
//        File f = new File("E:/wangttthhh/",name.concat(".class"));
        File f = new File("E:/Workspace4/test-parent/test-component/target/classes/",name.replaceAll(".", "/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b=fis.read()) !=0 ) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        ClassLoader l = new T03_MSBClassLoader();
        Class<?> clazz = l.loadClass("com.sitech.wth.practice.mashibing.jvm.classloader.Hello");
        Hello hello = (Hello) clazz.newInstance();
        System.out.println(hello.m());

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
    }
}
