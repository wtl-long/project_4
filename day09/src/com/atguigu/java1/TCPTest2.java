package com.atguigu.java1;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
    实现TCP的网络编程
    例题2:客户端发送文件给服务端，服务端将文件保存在本地。

 */
public class TCPTest2
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
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            InetAddress byName = InetAddress.getByName("127.0.0.1");
            socket = new Socket(byName, 9999);
            outputStream = socket.getOutputStream();
            fileInputStream = new FileInputStream(new File("day09/1 (1).jpg"));

            byte[] bytes = new byte[1024];
            int lens;
            while ((lens = fileInputStream.read(bytes)) != -1)
            {
                byteArrayOutputStream.write(bytes, 0, lens);
            }

            outputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
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
            try
            {
                byteArrayOutputStream.close();
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
            serverSocket = new ServerSocket(9999);
            System.out.println("服务器接收数据中……");
            Socket accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fileOutputStream = new FileOutputStream("day09/10.jpg");
            byte[] bytes = new byte[1024];
            int lens;
            int count = 0;
            while ((lens = inputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes, 0, lens);
                count += lens;
            }

            System.out.println("成功获取来自" + accept.getLocalAddress().getHostName() + "的文件");
            System.out.println("文件的总长度为：" + count);
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
