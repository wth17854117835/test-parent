package com.sitech.wth.practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: wangth_oup
 * @date: 2020-08-20 17:30
 * @description: TCP通信的客户端：向服务器发送连接请求，给服务器发送数据，读取服务器读写的数据
 *  套接字：包含了IP地址和端口号的网络单位
 *  实现步骤：
 *      1.创建客户端对象Socket，构造方法绑定服务器的IP和端口
 *      2.使用Socket对象中的方法getOutputStream()获取网络字节输出流OutputStream对象
 *      3.使用网络字节输出流OutputStream对象中的方法write，给服务器发送数据
 *      4.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
 *      5.使用网络字节输入流InputStream对象中的read，读取服务器回写的数据
 *      6.释放资源Socket
*   注意：
 *      1.客户端与服务器端进行交互，必须使用Socket中提供的网络流，不能使用自己创建的流对象
 *      2.当我们创建客户端对象Socket的时候，就会去请求服务器和服务器经过3次握手建立连接通路
 *          如果服务器没有启动，会抛出异常
 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8085);

        OutputStream os = socket.getOutputStream();
        os.write("你好服务器".getBytes());

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));

        socket.close();
    }
}
