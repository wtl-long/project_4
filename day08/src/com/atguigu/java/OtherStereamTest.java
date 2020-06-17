package com.atguigu.java;

/*
其他流的使用
    1.标准的输入、输出流
    2.打印流
    3.数据流.

 */

import java.io.*;

public class OtherStereamTest
{
    public static void main(String[] args) throws Exception
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /*
        1.
        标准的输入、输出流
        System. in:标准的输入流，默认从键盘输入
        System. out:标准的输出流，默认从控制台输出

        1.1

        1.2
        System类的setIn(InputStream is) / setOut(PrintStream ps) 方式重新指定输入和输出的斗
        1.3练习:
        从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作,
        直至当输入“e”或者“exit"时，退出程序。
        方法一:使用Scanner实现，调用next()返回一个字符串
        方法二:使用System. in实现。System. in--->转换流 ---> BufferedReader 的readLine

    */

    private static void test1()
    {
        //System.in输入的是字节--->字符
        BufferedReader bufferedReader = null;
        try
        {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true)
            {
                System.out.println("请输入字符串：");
                String data = bufferedReader.readLine();
                if (data.equalsIgnoreCase("e") || data.equalsIgnoreCase("exit"))
                {
                    System.out.println("程序结束");
                    break;
                }

                String lowCase = data.toUpperCase();
                System.out.println(lowCase);

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                bufferedReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

/*
    2.打印流: PrintStream 和Printwriter
    2.1提供了一-系列重 载的print()和println()
    2.2练习:
*/

    public static void test2()
    {
        PrintStream printStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("d:/a.txt");
            //设置为自动刷新缓冲区
            printStream = new PrintStream(fileOutputStream, true);
            //设置输入位置为文件
            System.setOut(printStream);
            int count = 1;

            System.out.println("第" + count + "行");
            for (int i = 0; i < 255; i++)
            {
                //ASCII--->字符--->写到文件
                System.out.print((char) i);
                if (i % 50 == 0)
                {
                    count++;
                    //50个数据换一行
                    System.out.println();
                    System.out.println("第" + count + "行");
                }

            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            printStream.close();
        }

    }

    /*
        3.数据流
        3.1 DataInputStream 和DataOutputStream
        3.2作用:用于读取或写出基本数据类型的变量或字符串
    */

    public static void test3()
    {
        //DataInputStream去读，直接打开会乱码
        DataOutputStream dataOutputStream = null;
        try
        {
            dataOutputStream = new DataOutputStream(new FileOutputStream("D:/A.TXT"));
            dataOutputStream.writeUTF("张三");
            dataOutputStream.flush();
            dataOutputStream.writeInt(20);
            dataOutputStream.flush();
            dataOutputStream.writeByte(97);
            dataOutputStream.flush();
            dataOutputStream.writeInt(97);
            dataOutputStream.flush();

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                dataOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void test4() throws IOException
    {
        //读取要和写入数据的类型顺序一致
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("d:/a.txt"));
        String s = dataInputStream.readUTF();
        int i = dataInputStream.readInt();
        byte b = dataInputStream.readByte();
        int i1 = dataInputStream.readInt();
        System.out.println(s + i + b + i1);

        dataInputStream.close();
    }
}
