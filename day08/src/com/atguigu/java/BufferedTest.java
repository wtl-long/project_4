package com.atguigu.java;

/*
处理流之一:缓冲流的使用
1.缓冲流:
    BufferedInputStream
    BufferedOutputStream
    BufferedReader
    BufferedWriter
    
2.作用:提供流的读取、写入的速度
    提高读写速度的原因:内部提供了一个缓冲区

3.处理流，就是“套接”在已有的流的基础上。

 */

import java.io.*;

public class BufferedTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    //使用BufferedReader、BufferedWriter实现文件的复制
    private static void test2()
    {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader("day08/hello1.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("day08/hello2.txt"));

            //读写操作
            //方式一：使用char数组
//            char[] chars = new char[1024];
//            int lens;
//            while ((lens = bufferedReader.read(chars)) != -1)
//            {
//                bufferedWriter.write(chars, 0, lens);
//            }

            //方式二：使用String
            String data;
            while ((data = bufferedReader.readLine()) != null)
            {
                //方法一:
//                bufferedWriter.write(data+"\n"); //data中不包含换行符

                //方法二：
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

            if (bufferedWriter != null)
            {
                try
                {
                    bufferedWriter.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

        }


    }

    //缓冲流实例
    private static void test1()
    {
        //1.造文件
        File srcFile = new File("day08/魔术师.png");
        File destFile = new File("day08/魔术师3.jpg");
        //2.造流
        //2.1造节点流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //2.2造缓冲流
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[10];
            int reads;
            while ((reads = bis.read(bytes)) != -1)
            {
                bos.write(bytes, 0, reads);
            }

            //4.资源关闭
            //要求:先关闭外层的流，再关闭内层的流
            bos.close();
            bis.close();
            //说明:关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略
//            fos.close();
//            fis.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
