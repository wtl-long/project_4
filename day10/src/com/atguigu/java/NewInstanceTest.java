package com.atguigu.java;

/*
    通过反射创建运行时类的对象
 */

import java.util.Random;

public class NewInstanceTest
{
    public static void main(String[] args) throws Exception
    {
//        test1();
        test2();
    }

    public static void test1() throws Exception
    {
        Class<Person> personClass = Person.class;
        /*
             创建对应的运行时类的对象，内部调用了空参构造器

             要想此方法正常的创建运行时类的对象，要求:
                1.运行时类必须提供空参的构造器
                2.空参的构造器的访问权限得够。通常，设置为public。

                在javabean中要求提供一个public的空参构造器。 原因:
                1.便于通过反射，创建运行时类的对象
                2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器

         */
        Person person = personClass.newInstance();

        System.out.println(person);
    }

    public static void test2()
    {
        //随机产生0、1、2
        int i = new Random().nextInt(3);
        String classPath = "";
        switch (i)
        {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.atguigu.java.Person";
                break;
        }

        Object instance = null;
        try
        {
            instance = getInstance(classPath);
            System.out.println(instance.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    /*
        创建一个指定类的对象。
        classPath:指定类的全类名
    */

    public static Object getInstance(String classPath) throws Exception
    {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

}
