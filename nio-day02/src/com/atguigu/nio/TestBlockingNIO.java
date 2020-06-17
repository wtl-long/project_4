package com.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
    一、使用NIO完成网络通信的三个核心:
    1.通道(Channel) :负责连接
    java.nio. channels . Channel接口:
    | --SelectableChannel
        |-- SocketChannel
        |--ServerSocketChannel
        | - -DatagramChannel

        | --Pipe.SinkChannel
        | --Pipe. SourceChannel

    2.缓冲区(Buffer) :负责数据的存取

    3.选择器(Selector) :是SelectableChannel 的多路复用器。用于监控SelectableChannel的IO状况

 */
public class TestBlockingNIO
{
    public static void main(String[] args) throws Exception
    {
//        TestBlockingNIO2.server();
//        client();
//        TestNonBlockingNIO.server();
        TestNonBlockingNIO2.receive();
    }

    //客户端
    public static void client()
    {
        SocketChannel open = null;
        FileChannel open1 = null;
        try
        {
            //1. 获取通道
            open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            //2. 分配指定大小的缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            open1 = FileChannel.open(Paths.get("d:/a.txt"), StandardOpenOption.READ);

            //3. 读取本地文件并发送到服务端
            while (open1.read(allocate) != -1)
            {
                allocate.flip();
                open.write(allocate);
                allocate.clear();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //4. 关闭通道
            try
            {
                open.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                open1.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    //服务端
    public static void server()
    {
        ServerSocketChannel open = null;
        FileChannel open1 = null;
        SocketChannel accept = null;
        try
        {
            //1. 获取通道
            open = ServerSocketChannel.open();

            open1 = FileChannel.open(Paths.get("d:/b.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            //2. 绑定连接
            ServerSocketChannel bind = open.bind(new InetSocketAddress(9898));

            //3. 获取客户端连接的通道
            accept = open.accept();

            //4. 分配指定大小的缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            //5. 接收客户端的数据,并保存到本地
            while (accept.read(allocate) != -1)
            {
                allocate.flip();
                open1.write(allocate);
                allocate.clear();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //6. 关闭通道
            try
            {
                open.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                open1.close();
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

        }


    }

}
