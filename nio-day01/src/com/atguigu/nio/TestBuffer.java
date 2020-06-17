package com.atguigu.nio;

import java.nio.ByteBuffer;

/*
    一、缓冲区(Buffer) :在Java NIO中负责数据的存职。缓冲区就是数组。用于存储不同数据类型的数据

    根据数据类型不同(boolean除外)，提供了相应类型的缓冲区:
    ByteBuffer
    CharBuffer
    ShortBuffer
    IntBuffer
    LongBuffer
    FloatBuffer
    DoubleBuffer

    上述缓冲区的管理方式几乎一致，通过allocate() 获取缓冲区

    二、缓冲区存取数据的两个核心方法:
    put():存入数据到缓冲区中
    get() :获取缓冲区中的数据

    三、缓冲区中的四个核心属性:.
    capacity :容量, 表示缓冲区中最大存储数据的容量。一旦声明不能改变。
    limit :界限，表示缓冲区中可以操作数据的大小。(limit 后数据不能进行读写)
    position :位置,表示缓冲区中正在操作数据的位置。
    mark :标记， 表示记录当前position的位置。可以通过reset()恢复到mark的位置

    0<=mark<=position <= limit <= capacity

    五、直接缓冲区与非直接缓冲区:
    非直接缓冲区:通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
    直接缓冲区:通过allocateDirect()方法分配直接缓冲区， 将缓冲区建立在物理内存中。可以提高效率





 */
public class TestBuffer
{
    public static void main(String[] args)
    {
        test1();
//        test2();
        test3();
    }

    public static void test1()
    {
        String str = "abcdl";

        //1. 分配一个指定大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        System.out.println("-----------allocate---------");
        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        //2.利用put()存入数据到缓冲区中
        allocate.put(str.getBytes());

        System.out.println("-----------put------------");

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        //切换到读取数据模式(flip)
        allocate.flip();

        System.out.println("-----------flip------------");

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        //4.利用get()读取缓冲区中的数据
        byte[] bytes = new byte[allocate.limit()];
        allocate.get(bytes);
        System.out.println(new String(bytes));

        System.out.println("-----------get------------");


        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        //5. rewind:可重复读数据
        allocate.rewind();

        System.out.println("-----------rewind------------");

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        //6. clear清空缓冲区,但是缓冲区中的数据依然存在，只是处于“被遗忘”状态
        allocate.clear();

        System.out.println("-----------clear------------");

        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());

        System.out.println((char) allocate.get());

        System.out.println(allocate.position());
    }

    public static void test2()
    {
        String str = "abcds";
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        allocate.put(str.getBytes());

        allocate.flip();

        byte[] bytes = new byte[allocate.limit()];
        allocate.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, 2));

        System.out.println(allocate.position());    //2

        //mark:标记
        allocate.mark();

        allocate.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));
        System.out.println(allocate.position());    //4

        //reset:恢复到mark的位置
        allocate.reset();
        System.out.println(allocate.position());        //2

        //判断缓冲区中是否还有数据
        if (allocate.hasRemaining())
        {
            //获取缓冲区可以操作的数量
            System.out.println(allocate.remaining());
        }

    }

    public static void test3()
    {
        //直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());
    }

}


