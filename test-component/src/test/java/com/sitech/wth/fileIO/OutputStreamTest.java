package com.sitech.wth.fileIO;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 14:38
 * @description:
 **/
public class OutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos=new FileOutputStream("E:\\wangttthhh\\test1OutputStream.txt");
        //写字节数组
        byte[] bytes={97,98,99}; //a b c
        fos.write(bytes,0,bytes.length);//第二个参数是下标,第三个是写入的个数
        fos.write("\r\n".getBytes());//换行
        //写字符数组,只能将字符串转成字节数组,用String类方法getBytes
        fos.write(97);//写入了a字符
        fos.write("zpc".getBytes());
        fos.close();
    }

}
