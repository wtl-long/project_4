package com.atguigu.java;

/*
一、流的分类:
    1.操作数据单位:字节流、字符流
    2.数据的流向:输入流、输出流
    3.流的角色:节点流、处理流

二、流的体系结构
    抽象基类                      节点流(或文件流)                   缓冲流(处理流的一种)
    InputStream                 FileInputStream                 BufferedInputStream
    FileInputStream             FileOutputStream                BufferedOutputStream
    Reader                      FileReader                      BufferedReader
    Writer                      FileWriter                      BufferedWriter

 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest
{
    public static void main(String[] args) throws Exception
    {
//        testFileReader();
        testFileWriter();
    }


    private static void testFileReader() throws Exception
    {
        File file = new File("day08/hello.txt");
        FileReader fileReader = new FileReader(file);

        //数据的读入
        //read():返回读入的一一个字符。如果达到文件末尾，返回-1
        int read = fileReader.read();
        while (read != -1)
        {
            System.out.print((char) read);
            read = fileReader.read();
        }

        //关闭
        fileReader.close();

    }

    /*
        从内存中写出数据到硬盘的文件里。

        说明:
            1.输出操作，对应的File可以不存在的。并不会报异常
            2.
            File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
            File对应的硬盘中的文件如果存在:
                 如果流使用的构造器是: FileWriter(file, false) / FileWriter(file):对原有文件的覆盖
                 如果流使用的构造器是: FileWriter(file, true)追加写入

    */
    public static void testFileWriter() throws IOException
    {
        //1.提供File类的对象，指明写出到的文件
        File file = new File("hello1.txt");
        //2.提供FileWriter的对象，用于数据的写出
        FileWriter fw = new FileWriter(file, true);
        //3.写出的操作
        fw.write("I have a dream!\n");
        fw.write("you need to have a dream!");
        //4.流资源的关闭
        fw.close();

    }
}
