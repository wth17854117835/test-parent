package com.sitech.wth.fileIO;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 13:39
 * @description: Reader是字符流读的抽象基类(输入流，读取文件的抽象基类)
 **/
public class FileReaderTest {
    public static void main(String[] args) {
        FileReader fr1 = null;
        FileReader fr2 = null;
        try {
            fr1 = new FileReader("E:\\wangttthhh\\test1.txt");
            int len = 0;// read方法返回读取的单个字符的ASCII码(可以转换成字符输出)
            while ((len = fr1.read()) != -1) {
                System.out.print((char) len);
            }
            System.out.println("\n******将字符读入缓冲数组再输出*****");
            // 定义字符数组
            char[] buf = new char[512];// 将512*2字节字符读入缓冲数组
            fr2 = new FileReader("E:\\wangttthhh\\test2.txt");
            while ((len = fr2.read(buf)) != -1) {//返回值len表示读取的有效字符的长度
                System.out.print(new String(buf,0,len));//将字符数组包装成String
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr1 != null) {
                    fr1.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("文件关闭失败");
            }
            try {
                if (fr2 != null) {
                    fr2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
