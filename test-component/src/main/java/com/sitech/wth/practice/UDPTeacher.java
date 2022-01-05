package com.sitech.wth.practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: wangth_oup
 * @date: 2021-11-04 11:27
 * @description:
 **/
public class UDPTeacher {
    public static void main(String[] args) throws IOException {
        System.out.println("老师上线了");
        //1.准备套接字 DatagramSocket，指定接受方的端口
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //2.准备数据包，四个参数（传送数据转为字节数组，字节数组的长度，封装接收方的IP，指定接收方的端口号）
//        String str = "你好，学生";
//        byte[] bytes = str.getBytes();
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //3.接受对方的数据包，填充到bytes字节数组
        datagramSocket.receive(datagramPacket);
        //4.取出数据
        byte[] data = datagramPacket.getData();
        String s = new String(data,0,datagramPacket.getLength());
        System.out.println(s);
        //5.关闭资源
        datagramSocket.close();
    }
}
