package com.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @create 2020-06-12 15:37
 **/
public class TestBlockingNIO2
{
    public static void main(String[] args)
    {
        client();
    }

    public static void client()
    {
        SocketChannel open = null;
        FileChannel open1 = null;
        try
        {
            open = SocketChannel.open(new InetSocketAddress("localhost", 6666));
            open1 = FileChannel.open(Paths.get("d:/homework.wmv"), StandardOpenOption.READ);

            ByteBuffer allocate = ByteBuffer.allocate(1024);

            while (open1.read(allocate) != -1)
            {
                allocate.flip();
                open.write(allocate);
                allocate.clear();
            }

            open.shutdownOutput();

            //接受服务端的反馈
            int lens;
            while ((lens = open.read(allocate)) != -1)
            {
                allocate.flip();
                System.out.println(new String(allocate.array(), 0, lens));
                allocate.clear();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
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

    public static void server()
    {
        ServerSocketChannel open = null;
        SocketChannel accept = null;
        FileChannel open1 = null;
        try
        {
            open = ServerSocketChannel.open();
            open.bind(new InetSocketAddress(6666));

            accept = open.accept();

            open1 = FileChannel.open(Paths.get("e:/a.wmv"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            ByteBuffer allocate = ByteBuffer.allocate(1024);

            while (accept.read(allocate) != -1)
            {
                allocate.flip();
                open1.write(allocate);
                allocate.clear();
            }

            //发送反馈给客户端
            allocate.put("接受数据成功!".getBytes());
            allocate.flip();
            accept.write(allocate);


        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
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
