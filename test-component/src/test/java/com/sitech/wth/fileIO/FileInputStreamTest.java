package com.sitech.wth.fileIO;

import java.io.*;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 14:34
 * @description:
 **/
public class FileInputStreamTest {

    public static void main(String[] args)throws IOException {
        FileInputStream fis=new FileInputStream("E:\\wangttthhh\\test1.txt");
        byte[] bytes=new byte[1024];//1kb
        int len=0;
        len=fis.read(bytes);//len表示有效个数
        System.out.println(new String(bytes));
        System.out.println(len);

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
    }
}
