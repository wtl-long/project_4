package com.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @create 2020-06-12 16:05
 **/
public class TestNonBlockingNIO
{
    public static void main(String[] args)
    {
        client();
    }

    public static void client()
    {
        SocketChannel localhost = null;
        try
        {
            //1. 获取通道
            localhost = SocketChannel.open(new InetSocketAddress("localhost", 8989));

            //2. 切换成非阻塞模式
            localhost.configureBlocking(false);

            //3. 分配缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            //4. 发送数据给服务器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext())
            {
                String str = scanner.next();
                allocate.put((new Date().toString() + "\n" + str).getBytes());
                allocate.flip();
                localhost.write(allocate);
                allocate.clear();
            }


        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {

            //5. 关闭通道
            try
            {
                localhost.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    public static void server() throws Exception
    {
        //1. 获取通道
        ServerSocketChannel open = ServerSocketChannel.open();

        //2. 切换成非阻塞模式
        open.configureBlocking(false);

        //3. 绑定连接
        open.bind(new InetSocketAddress(8989));

        //4. 获取选择器
        Selector open1 = Selector.open();

        //5. 将通道注册到选择器上，并且指定“监听接收事件”
        open.register(open1, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (open1.select() > 0)
        {
            //7. 获取当前选择器中所有注册的“选择键（已就绪的监听事件）”
            Iterator<SelectionKey> iterator = open1.selectedKeys().iterator();

            while (iterator.hasNext())
            {
                //8. 获取准备就绪的事件
                SelectionKey next = iterator.next();

                //9. 判断具体是什么事件准备就绪
                if (next.isAcceptable())
                {
                    //10. 若"接收就绪"获取客户端连接
                    SocketChannel accept = open.accept();

                    //11. 切换非阻塞模式
                    accept.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    accept.register(open1, SelectionKey.OP_READ);

                } else if (next.isReadable())
                {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel channel = (SocketChannel) next.channel();

                    //14. 读取数据
                    ByteBuffer allocate = ByteBuffer.allocate(1024);

                    int lens = 0;

                    while ((lens = channel.read(allocate)) > 0)
                    {
                        allocate.flip();
                        System.out.println(new String(allocate.array(), 0, lens));
                        allocate.clear();

                    }

                }

                //15. 取消选择键SelectionKey
                iterator.remove();

            }

        }

    }
}
