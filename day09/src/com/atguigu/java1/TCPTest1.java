package com.atguigu.java1;

/*
    实现TCP的网络编程
    例子1:客户端发送信息给服务端，服务端将数据显示在控制台

 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest1
{
    public static void main(String[] args)
    {
        client();
    }

    //客户端
    public static void client()
    {
        //1. 创建Socket对象，指明服务器端的IP和端口号
        Socket socket = null;
        OutputStream outputStream = null;
        try
        {
            InetAddress byName = InetAddress.getByName("192.168.31.1");
            socket = new Socket(byName, 8888);
            //2. 获取输出流，用于输出数据
            outputStream = socket.getOutputStream();
            //3. 写出数据操作
            outputStream.write("你好，我是客户端".getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //4. 关闭资源
            try
            {
                outputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    //服务端
    public static void server()
    {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            //1. 创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8888);
            //2. 表示接收来自客户端的Socket
            accept = serverSocket.accept();
            //3. 获取输入流
            inputStream = accept.getInputStream();
            //不建议这样写，可能有乱码
//        byte[] bytes = new byte[20];
//        int lens;
//        while ((lens = inputStream.read(bytes)) != -1)
//        {
//            String str = new String(bytes, 0, lens);
//            System.out.println(str);
//        }
            //4. 获取输入流中的数据
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[5];
            int lens;
            while ((lens = inputStream.read(bytes)) != -1)
            {
                byteArrayOutputStream.write(bytes, 0, lens);
            }

            //一次把所有的字节转化，就可以防止乱码
            System.out.println(byteArrayOutputStream.toString());
            System.out.println("收到了来自于：" + accept.getInetAddress().getHostAddress() + "的信息");
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //5. 关闭资源
            try
            {
                byteArrayOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                inputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                accept.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                serverSocket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }


}
