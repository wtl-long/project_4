package com.atguigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @create 2020-06-12 19:20
 **/
public class TestNonBlockingNIO2
{

    public static void main(String[] args)
    {
        send();
    }

    public static void send()
    {
        DatagramChannel open = null;
        try
        {
            open = DatagramChannel.open();

            open.configureBlocking(false);

            ByteBuffer allocate = ByteBuffer.allocate(1024);

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext())
            {
                String str = scanner.next();
                allocate.put(((new Date()).toString() + "\n" + str).getBytes());
                allocate.flip();
                open.send(allocate, new InetSocketAddress("localhost", 8989));
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
        }


    }

    public static void receive() throws IOException
    {
        DatagramChannel open = DatagramChannel.open();

        open.configureBlocking(false);

        open.bind(new InetSocketAddress(8989));

        Selector open1 = Selector.open();

        open.register(open1, SelectionKey.OP_READ);

        while (open1.select() > 0)
        {
            Iterator<SelectionKey> iterator = open1.selectedKeys().iterator();

            while (iterator.hasNext())
            {
                SelectionKey next = iterator.next();

                if (next.isReadable())
                {
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    open.receive(allocate);
                    allocate.flip();
                    System.out.println(new String(allocate.array(), 0, allocate.limit()));
                    allocate.clear();
                }
                iterator.remove();
            }

        }

    }

}


