package com.atguigu.java1;

/*
    实现TCP的网络编程
    例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给
    客户端。并关闭相应的连接。

 */

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest3
{
    public static void main(String[] args)
    {

        client();
    }

    public static void client()
    {
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream1 = null;
        try
        {
            InetAddress byName = InetAddress.getByName("127.0.0.1");
            socket = new Socket(byName, 7878);
            outputStream = socket.getOutputStream();
            fileInputStream = new FileInputStream(new File("day09/10.jpg"));

            byte[] bytes = new byte[1024];
            int lens;
            while ((lens = fileInputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, lens);
            }

            //提示传输完成
            socket.shutdownOutput();

            //接收来自于服务器端的数据，并显示到控制台上
            InputStream inputStream = socket.getInputStream();
            byteArrayOutputStream1 = new ByteArrayOutputStream();
            int len1;
            byte[] bytes1 = new byte[1024];
            while ((len1 = inputStream.read(bytes1)) != -1)
            {
                byteArrayOutputStream1.write(bytes1, 0, len1);
            }

            System.out.println(byteArrayOutputStream1.toString());

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                byteArrayOutputStream1.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
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
            try
            {
                fileInputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    public static void server()
    {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            serverSocket = new ServerSocket(7878);
            System.out.println("服务器接收数据中……");
            Socket accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fileOutputStream = new FileOutputStream("day09/11.jpg");
            byte[] bytes = new byte[1024];
            int lens;
            while ((lens = inputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes, 0, lens);
            }

            System.out.println("成功获取来自" + accept.getLocalAddress().getHostName() + "的文件");

            //服务端反馈客户端
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("你好，照片已经收到，非常漂亮".getBytes());


        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                fileOutputStream.close();
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
                serverSocket.close();
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
