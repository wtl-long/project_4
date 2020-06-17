package com.atguigu.java;

/*
    了解类的加载器
*/

import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest
{
    public static void main(String[] args) throws Exception
    {
//        test1();
        test2();
    }

    public static void test1()
    {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统类加载器的getParent：获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        //调用扩展类加载器的getParent：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);

    }

    /*
        Properties:用来读取配置文件
     */
    public static void test2() throws Exception
    {
        Properties properties = new Properties();
        //读取配置文件方式一：
//        FileInputStream fileInputStream = new FileInputStream("day10/jdbc.properties");
//        properties.load(fileInputStream);


        //读取配置文件方式二：使用ClassLoader
        //配置文件默认识别为:当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user);
        System.out.println(password);
    }
}
