package com.atguigu.java2;

import com.atguigu.java1.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @create 2020-06-05 18:30
 **/
public class OtherTest
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    /*
        获取构造器结构
     */
    public static void test1()
    {
        Class<Person> personClass = Person.class;
        //getConstructors():获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor c : constructors)
        {
            System.out.println(c);
        }

        System.out.println();

        //getDeclaredConstructors():获取当前运行时类中声明的所有的构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor c : declaredConstructors)
        {
            System.out.println(c);
        }
    }

    //获取运行时类的父类
    public static void test2()
    {
        Class<Person> personClass = Person.class;
        Class<? super Person> superclass = personClass.getSuperclass();
        System.out.println(superclass.getName());
    }

    //获取运行时类的带泛型的父类
    public static void test3()
    {
        Class<Person> personClass = Person.class;
        Type genericSuperclass = personClass.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    //获取运行时类的带泛型的父类
    public static void test4()
    {
        Class<Person> personClass = Person.class;
        Type genericSuperclass = personClass.getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = p.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class) actualTypeArguments[0]).getName());

    }

    //获取运行时类实现的接口
    public static void test5()
    {
        Class<Person> personClass = Person.class;
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class c : interfaces)
        {
            System.out.println(c);
        }

        System.out.println();

        Class<?>[] interfaces1 = personClass.getSuperclass().getInterfaces();
        for (Class c : interfaces1)
        {
            System.out.println(c);
        }

    }

    //获取运行时类所在的包
    public static void test6()
    {
        Class<Person> personClass = Person.class;
        Package aPackage = personClass.getPackage();
        System.out.println(aPackage);

    }

    //获取运行时声明的注解
    public static void test7()
    {
        Class<Person> personClass = Person.class;
        Annotation[] annotations = personClass.getAnnotations();
        for (Annotation a : annotations)
        {
            System.out.println(a);
        }

    }

}
