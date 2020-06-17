package com.atguigu.java;

/*
处理流之二:转换流的使用
    1.转换流:属于字符流
        InputStreamReader:将一个字节的输入流转换为字符的输入流
        OutputStreamWriter:将一个 字符的输出流转换为字节的输出流

    2.作用:提供字节流与字符流之间的转换

    3.解码:字节、字节数组，--->字符数组、字符串
      编码:字符数组、字符串--->字节字节数组

    4.字符集
    ASCII:美国标准信息交换码。
        用一个字节的7位可以表示。
    IS08859-1:拉丁码表。欧洲码表
        用一个字节的8位表示。
    GB2312:中国的中文编码表。最多两个字节编码所有字符
    GBK:中国的中文编码表升级,融合了更多的中文文字符号。最多两个字节编码
    Unicode:国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。 所
    UTF-8:变长的编码方式，可用1-4 个字节来表示一个字符。

 */

import java.io.*;

public class InputStreamReaderTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    /*
        综合使用InputStreamReader、OutputStreamWriter

     */
    private static void test2()
    {
        File file = new File("day08/hello.txt");
        File file1 = new File("day08/hello_gbk.txt");

        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file1);

            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gbk");

            char[] chars = new char[20];
            int lens;
            while ((lens = inputStreamReader.read(chars)) != -1)
            {
                outputStreamWriter.write(chars, 0, lens);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                inputStreamReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                outputStreamWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    private static void test1()
    {
        InputStreamReader isr = null;
        try
        {
            FileInputStream fis = new FileInputStream("day08/hello.txt");
            isr = new InputStreamReader(fis, "UTF-8");

            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf)) != -1)
            {
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                isr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }


}
