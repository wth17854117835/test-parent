package com.sitech.wth.fileIO;

import java.io.*;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 13:59
 * @description: 复制文本文件
 * 读取源文件 FileReader
 * 写入到目的文件 FileWriter
 **/
public class CopyText {
    // 读取一个数组，写一个数组
    public static void copyText1() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("E:\\wangttthhh\\test1.txt");
            fw = new FileWriter("E:\\wangttthhh\\test1copy1.txt");
            char[] buff = new char[1024];
            int len = 0;
            while ((len = fr.read(buff)) != -1) {
                fw.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                throw new RuntimeException("文件读取关闭失败");
            }
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                throw new RuntimeException("文件写入关闭失败");
            }
        }
    }

    // 读取一个字符，写一个字符
    public static void copyText2() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("E:\\wangttthhh\\test1.txt");
            fw = new FileWriter("E:\\wangttthhh\\test1copy2.txt");
            int len = 0;
            while ((len = fr.read()) != -1) {
                fw.write((char) len);
            }
        } catch (Exception e) {
            throw new RuntimeException("文件复制失败");
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                throw new RuntimeException("文件读取关闭失败");
            }
            try {
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("文件写入关闭失败");
            }
        }
    }

    //读取一行写一行的方式，利用的缓冲区对象
    public static void copyText3(){
        FileReader fr=null;
        FileWriter fw=null;
        BufferedReader bfr=null;
        BufferedWriter bfw=null;
        try {
            fr=new FileReader("E:\\wangttthhh\\test1.txt");
            fw=new FileWriter("E:\\wangttthhh\\test1copy3.txt");
            bfr=new BufferedReader(fr);
            bfw=new BufferedWriter(fw);
            String sLine=null;
            while((sLine=bfr.readLine())!=null){
                bfw.write(sLine);
                //bfw.write("\r\n"); //windows下的换行
                bfw.newLine(); //写一个换行，具有跨平台
            }
        } catch (IOException e) {
            throw new RuntimeException("文件复制失败");
        }finally{
            try {
                if(bfr!=null)
                    bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bfw!=null)
                    bfw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        copyText1();
//        copyText2();
        copyText3();
        long cost = System.currentTimeMillis() - start;
        System.out.println("耗时：" + cost + "ms");
    }
}
