package com.sitech.wth.practice;

import repackaged.nl.flotsam.xeger.Xeger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author: wangth_oup
 * @date: 2020-08-20 17:45
 * @description:    TCP通信的服务器端：接收客户端的请求，读取客户端发送的数据，给客户端回写数据
 *  java.net.ServerSocket
 *  服务器端必须明确一件事，必须知道是哪个客户端请求的服务器
 *  可以使用accept方法获取到请求的客户端对象Socket
 *  实现步骤：
 *      1.创建服务器ServerSocket对象和系统要指定的端口号
 *      2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket
 *      3.使用Socket对象中的方法getInputStream()获取网络字节输入流InputStream对象
 *      4.使用网络字节输入流InputStream对象中的read，读取客户端发送的数据
 *      5.使用Socket对象中的方法getOutputStream()获取网络字节输出流OutputStream对象
 *      6.使用网络字节输出流OutputStream对象中的方法write，给客户端回写数据
 *      7.释放资源（Socket，ServerSocket）
 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
//        String regex = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}";
//        String regex = "^\\{\\$.*\\}$";
//        Xeger generator = new Xeger(regex);
//        for (int i = 0; i < 20; i++) {
//            String result = generator.generate();
//            System.out.println("========"+result);
//            assert result.matches(regex);
//        }

        ServerSocket serverSocket = new ServerSocket(8085);
        Socket socket = null;
        while (true) {
            //服务器一直监听
            socket = serverSocket.accept();
            new ServerThread(socket).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //业务代码
                    System.out.println("传统方式创建线程-》"+Thread.currentThread().getName());
                }
            }).start();

            new Thread(() -> {
                System.out.println("Hello Lambda!");
            }).start();
        }
//        InputStream is = socket.getInputStream();
////        byte[] bytes = new byte[1024];
////        int len = is.read(bytes);
////        System.out.println(new String(bytes,0,len));
//        DataInputStream dis = new DataInputStream(is);
//        System.out.println(dis.readUTF());
//
//        OutputStream os = socket.getOutputStream();
//        os.write("收到，谢谢".getBytes());
//        socket.close();
//        serverSocket.close();
    }
}

class ServerThread extends Thread {
    InputStream is = null;
    DataInputStream dis = null;
    OutputStream os = null;
    Socket s = null;

    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            is = s.getInputStream();
            dis = new DataInputStream(is);
            System.out.println(dis.readUTF());

            os = s.getOutputStream();
            os.write("收到，谢谢".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dis!=null){
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(s!=null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
