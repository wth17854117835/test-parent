package com.sitech.wth.fileIO;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 13:47
 * @description: Java中字符流写数据，不会直接写到目的文件中，写到内存中
 *          想将数据写到目的文件中，刷新，刷到目的文本中 flush()刷新
 * 流对象中的功能，调用了Windows系统中的功能来完成释放掉操作系统中的资源，简称：关闭流
 * close方法，关闭流之前，关闭的时候，先要刷新流中的数据
 * 但是，如果写文件的数据量很大，写一句刷一句才好
 **/
public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fw1 = null;
        FileWriter fw2 = null;// 关流的时候要分别处理异常
        try {
            fw1 = new FileWriter("E:\\wangttthhh\\test3.txt");
            fw1.write("作品z");
            fw2 = new FileWriter("E:\\wangttthhh\\test4.txt");
            char[] ch={'q','w','e','你'};
            //第二个参数表示开始写入字符处的偏移量(从第几个字符开始写),第三个参数表示写多少长度的数据
//            fw2.write(ch,1,ch.length-1); //we你
//            fw2.write(ch,0,ch.length-3); //q
//            fw2.write(ch,0,ch.length); //qwe你
            fw2.write(ch);//Writer能一次写入一个字符数组大小的内容;Reader能一次读出一个字符数组(相当于缓冲)大小的内容
            fw1.flush();// 字符流必须手动刷新才能真的写入文件
            fw2.flush();
        } catch (IOException e) {
            throw new RuntimeException("文件写入失败");
        } finally {
            try {
                if (fw1 != null) // 健壮性判断，流有可能为空(流对象没有建立成功)
                {
                    fw1.close();// 调用close方法时会自动刷新，但是不要都等到这一步才刷
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //开了几个流关闭几个，单独关闭(否则第一个关的时候抛了异常，第二个就没有机会关了)
            try {
                if (fw2 != null)
                    fw2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
