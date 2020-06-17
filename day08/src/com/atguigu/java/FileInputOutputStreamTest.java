package com.atguigu.java;

/*
    测试FiLeInputStream和FileOutpuStream的使用
    结论:
        1.对于文本文件(. txt,.java,.c,.cpp)，使用字符流处理
        2.对于非文本文件( . jpg, .mp3, . mp4, .avi, . doc,.ppt,...),使用字节流处理


 */

import java.io.*;

public class FileInputOutputStreamTest
{
    public static void main(String[] args)
    {
        testFileInputOutputStream();
    }

    private static void testFileInputOutputStream()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(new File("day08/魔术师.png"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("day08/魔术师1.png"));
            byte[] bytes = new byte[1024];
            int read;
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes,0,read);
            }
            System.out.println("写入完毕!");
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
