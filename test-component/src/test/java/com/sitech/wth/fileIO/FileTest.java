package com.sitech.wth.fileIO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 11:58
 * @description:
 * 此类的实例可能表示（也可能不表示）实际文件系统对象，如文件或目录。
 *    File类可以新建、删除和重命名文件和目录，但是File不能访问文件本身的内容，这要使用IO流。
 *    File对象的createNewFile()方法在磁盘上创建真实的文件
 **/
public class FileTest {
    public static void main(String[] args) throws IOException {
        File file=new File("test1");//此相对路径即表示相对JVM的路径
        File file2=new File(".");//以当前路径来创建一个File对象
        File file3=new File("E:\\workplace\\IO\\jaa.txt");//系统不一定就存在jaa.txt这个文件
        System.out.println("file.getName()\t"+file.getName());
        System.out.println("file2.getName()\t"+file2.getName());
        System.out.println("file3.getName()\t"+file3.getName());
        System.out.println("file.getParent()\t"+file.getParent());
        System.out.println("file2.getParent()\t"+file2.getParent());
        System.out.println("file3.getParent()\t"+file3.getParent());
        System.out.println("file.getAbsolutePath()\t"+file.getAbsolutePath());
        System.out.println("file.getAbsoluteFile()\t"+file.getAbsoluteFile());
        System.out.println("file.getAbsoluteFile().getParent()\t"+file.getAbsoluteFile().getParent());
        System.out.println("file2.getAbsolutePath()\t"+file2.getAbsolutePath());
        System.out.println("file3.getAbsolutePath()\t"+file3.getAbsolutePath());

        File file4=new File("E://FileTest//test1.doc");
        System.out.println("file4.exists()\t"+file4.exists());
        //在系统中创建一个文件,注意test1.doc前面的目录一定要是真实存在的,否则执行createNewFile方法会报错
        if(!file4.exists()){
            file4.createNewFile();
            System.out.println("file4.exists()\t"+file4.exists());
        }
        File file5=new File("E:\\zpc");
        System.out.println("file5.mkdir()"+file5.mkdir());//在系统中创建一个File对象所对应的目录
        File file6=new File("E:\\logs");
        String fileList[]=file6.list();
        String fileList1[]=file6.list(new MyFilenameFilter());
        System.out.println("=======E:\\logs目录下的所有文件和路径如下=======");
        for(String s:fileList){
            System.out.println(s);
        }
        //File的静态方法listRoots列出所有的磁盘根路径
        File[] roots= File.listRoots();
        System.out.println("======系统所有根路径=======");
        for(File f:roots){
            // C:\ D:\ E:\
            System.out.println(f);
        }

        //在F:\\zpc目录下创建一个临时文件,并指定当JVM退出时删除该文件
        File temFile=File.createTempFile("zpca", ".txt",file5);
        temFile.deleteOnExit();
        //通过挂起当前线程5秒,会看到临时文件被创建5秒后由于程序执行完毕,JVM退出,该文件又自动删除
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyFilenameFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.endsWith(".java")||new File(name).isDirectory();
        }
    }

}
