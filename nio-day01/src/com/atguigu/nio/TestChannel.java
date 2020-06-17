package com.atguigu.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/*
    一、通道(Channel) :用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。
    Channel本身不存储数据，因此需要配合缓冲区进行传输。

    二、通道的主要实现类
    java.nio.channels.Channel接口:
    |-- FileChannel
        |-- SocketChannel
        |--ServerSocketChannel
        |--DatagramChannel

    三、获取通道
    1. Java 针对支持通道的类提供了getChannel() 方法
        本地IO:
            FileInputStream/FileOutputStream
            RandomAccessFile

        网络I0:
            Socket
            ServerSocket
            DatagramSocket

    2. 在JDK1.7中的NIO.2针对各个通道提供了静态方法open()

    3. 在JDK 1.7中的NI0.2 的Files工具类的newByteChannel()

    四、通道之间的数据传输
    transferFrom( )
    transferTo( )

    五、分散(Scatter )与聚集(Gather )
    分散读取(Scattering Reads) :将通道中的数据分散到多个缓冲区中
    聚集写入(Gathering Writes) :将多个缓冲区中的数据聚集到通道中

    六、字符集：Charset
    编码:字符串->字节数组
    解码:字节数组->字符串



 */
public class TestChannel
{
    public static void main(String[] args) throws Exception
    {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        TestBlockingNIO.server();
    }

    //字符集
    public static void test6() throws Exception
    {
        Charset gbk = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();

        //获取解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer allocate = CharBuffer.allocate(1024);
        allocate.put("尚硅谷威武！");
        allocate.flip();

        //编码
        ByteBuffer encode = charsetEncoder.encode(allocate);

        for (int i = 0; i < encode.limit(); i++)
        {
            System.out.println(encode.get());
        }

        //解码
        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);

        System.out.println(decode.toString());

        System.out.println("-------------------------------");

        Charset charset = Charset.forName("UTF-8");     //GBK编码UTF解码，出现乱码
//        //编码
//        CharsetEncoder charsetEncoder1 = charset.newEncoder();
//        //解码
//        CharsetDecoder charsetDecoder1 = charset.newDecoder();
        encode.flip();
        CharBuffer decode1 = charset.decode(encode);
        System.out.println(decode1.toString());

    }

    //字符集
    public static void test5()
    {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
        for (Map.Entry<String, Charset> s : entries)
        {
            System.out.println(s.getKey() + "=" + s.getValue());
        }

    }


    //分散和聚集
    public static void test4()
    {
        RandomAccessFile rw = null;
        FileChannel channel = null;
        try
        {
            rw = new RandomAccessFile("d:/a.txt", "rw");
            //1. 获取通道
            channel = rw.getChannel();

            //2. 分配指定大小的缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(100);
            ByteBuffer allocate1 = ByteBuffer.allocate(1024);

            //3. 分散读取
            ByteBuffer[] bufs = {allocate, allocate1};
            channel.read(bufs);

            for (ByteBuffer b : bufs)
            {
                b.flip();
            }

            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
            System.out.println("-----------------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

            //4. 聚集写入
            RandomAccessFile rw1 = new RandomAccessFile("d:/b.txt", "rw");
            FileChannel channel1 = rw1.getChannel();
            channel1.write(bufs);

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                rw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                channel.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    //通道之间的数据传输(直接缓冲区)
    public static void test3()
    {
        long l = System.currentTimeMillis();
        Instant now = Instant.now();

        FileChannel open1 = null;
        FileChannel open2 = null;
        try
        {
            open1 = FileChannel.open(Paths.get("D:/homework.wmv"), StandardOpenOption.READ);
            open2 = FileChannel.open(Paths.get("D:/homeword1.wmv"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

//            open1.transferTo(0, open1.size(), open2);
            open2.transferFrom(open1, 0, open1.size());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                open1.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                open2.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        long l1 = System.currentTimeMillis();
        Instant now1 = Instant.now();
        System.out.println("耗费的时间是（System）：" + (l1 - l));
        System.out.println("耗费的时间是：" + Duration.between(now, now1).toMillis());

    }


    //利用通道实现文件的复制(内存映射文件)
    public static void test2()
    {

        long l = System.currentTimeMillis();
        FileChannel open = null;
        FileChannel open1 = null;
        try
        {
            open = FileChannel.open(Paths.get("F:\\IDEA\\IDEA_workspace\\project_1\\nio-day01\\src/father.png"), StandardOpenOption.READ);
            open1 = FileChannel.open(Paths.get("F:\\IDEA\\IDEA_workspace\\project_1\\nio-day01\\src/father3.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //内存映射文件
            MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
            MappedByteBuffer map1 = open1.map(FileChannel.MapMode.READ_WRITE, 0, open.size());

            //直接对缓冲区进行数据的读写操作
            byte[] bytes = new byte[map.limit()];
            map.get(bytes);
            map1.put(bytes);
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
        long l1 = System.currentTimeMillis();
        System.out.println("内存映射文件耗费的时间是：" + (l1 - l));


    }


    //利用通道实现文件的复制(非直接缓冲区)
    public static void test1()
    {
        long l = System.currentTimeMillis();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel channel = null;
        FileChannel channel1 = null;
        try
        {
            File file = new File("F:\\IDEA\\IDEA_workspace\\project_1\\nio-day01\\src/father.png");
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream("F:\\IDEA\\IDEA_workspace\\project_1\\nio-day01\\src/father2.png");

            //1. 获取通道
            channel = fileInputStream.getChannel();
            channel1 = fileOutputStream.getChannel();

            //2. 分配指定大小的缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            //3. 将通道中的数据存入缓冲区中
            while (channel.read(allocate) != -1)
            {
                allocate.flip();    //切换到读取模式
                //4. 将缓冲区的数据写入通道
                channel1.write(allocate);
                allocate.clear();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                channel1.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                channel.close();
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
                fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        long l1 = System.currentTimeMillis();
        System.out.println("耗费的时间是：" + (l1 - l));


    }

}
