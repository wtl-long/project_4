package com.atguigu.java;

/*
    对象流的使用
    1.ObjectInputStream和ObjectOutputStream

    2.作用:
    用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可
    以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。

    3.要想一个java对象是可序列化的，需要满足相应的要求。 见Person. java

    4.序列化机制:
        对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
        二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
        当其它程序获取了这种二进制流，就可以恢复成原来的Java对象



 */

import java.io.*;

public class ObjectInOutputStreamTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    /*
        序列化过程:将内存中的java对象保存到磁盘中或通过网络传输出却
        使用ObjectOutputStream实现
    */
    private static void test1()
    {
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:/c.dat"));
            objectOutputStream.writeObject(new String("我爱coding"));
            objectOutputStream.writeObject(new Person(50, "张三"));
            objectOutputStream.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                objectOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
        反序列化:将磁盘文件中的对象还原为内存中的一-个java对象

     */
    public static void test2()
    {
        ObjectInputStream objectInputStream = null;
        try
        {
            objectInputStream = new ObjectInputStream(new FileInputStream("d:/c.dat"));
            Object o = objectInputStream.readObject();
            Object o1 = objectInputStream.readObject();
            String s = (String) o;
            System.out.println(s);
            System.out.println(o1);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                objectInputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

}
