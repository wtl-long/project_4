package com.atguigu.java4;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @create 2020-06-07 17:04
 **/
public class Java9Test
{
    public static void main(String[] args)
    {

    }

    public static void test1()
    {
        try
        {
            URL url = new URL("http://www.atguigu.com");


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    //java9新特性：钻石操作符（泛型）的升级
    public static void test2()
    {
        //钻石操作符与匿名内部类在java 8中不能共存。在java9 可以。
        Comparator<Object> comparator = new Comparator<>()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                return 1;
            }

        };

        //jdk7中的新特性:类型推断
        ArrayList<String> list = new ArrayList<>();

    }

    //java9特性六: try操作的升级
    public static void test3()
    {
//        Java8之前的资源关闭操作，在finally中书写

        //Java8之后的资源关闭操作，（）中的内容会自动关闭
        //要求自动关闭的资源的初始化必须放在try的一对小括号中(包括new)
        try (InputStreamReader reader = new InputStreamReader(System.in))
        {
            char[] cbuf = new char[20];
            int len;
            if ((len = reader.read(cbuf)) != -1)
            {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Java9中资源关闭操作:需要自动关闭的资源的实例化可以放在try的一对小括号外。
        //此时的资源属性是常量，声明为final的，不可修改
//        InputStreamReader reader = new InputStreamReader(System.in);
//        try (reader)
//        {
        //()中的实例不能更改
//        reader = null;
//        }

        // InputStreamReader reader = new InputStreamReader(System.in);
//        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        //关闭多个实例使用；（分号）隔开
//        try (reader; writer) {

    }
}