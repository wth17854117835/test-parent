package com.sitech.wth.practice;

import java.io.IOException;
import java.net.*;

/**
 * @author: wangth_oup
 * @date: 2021-11-04 11:27
 * @description: UDP通信
 **/
public class UDPStudent {

    public static void main(String[] args) throws IOException {
        System.out.println("学生上线了");
        //1.准备套接字 DatagramSocket
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        //2.准备数据包，四个参数（传送数据转为字节数组，字节数组的长度，封装接收方的IP，指定接收方的端口号）
        String str = "你好,laoshi";
        byte[] bytes = str.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 9999);
        //3.发送
        datagramSocket.send(datagramPacket);
        //4.关闭资源
        datagramSocket.close();
    }

}
