package com.atguigu.java;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
    RandomAccessFile的使用
        1. RandomAccessFile直接继承于java. lang. object类，实现了DataInput和DataOutput接口
        2. RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
        3. 如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
        如果写出到的文件存在，则会对原有文件内容进行覆盖。(默认情况下， 从头覆盖)



 */
public class RandomAccessFileTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    private static void test1()
    {
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2 = null;
        try
        {
            randomAccessFile = new RandomAccessFile("day09/1 (1).jpg", "r");
            randomAccessFile2 = new RandomAccessFile("day09/1 (1)1.jpg", "rw");
            byte[] bytes = new byte[1024];
            int lens;
            while ((lens = randomAccessFile.read(bytes)) != -1)
            {
                randomAccessFile2.write(bytes, 0, lens);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                randomAccessFile.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                randomAccessFile2.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    public static void test2()
    {
        RandomAccessFile randomAccessFile = null;
        try
        {
            randomAccessFile = new RandomAccessFile("d:/a.txt", "rw");
            //将文件指针调到角标为3的位置上
            randomAccessFile.seek(3);
            randomAccessFile.write("xyy".getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                randomAccessFile.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
