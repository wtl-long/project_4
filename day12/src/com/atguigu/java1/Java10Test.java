package com.atguigu.java1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @create 2020-06-07 21:56
 **/
public class Java10Test
{
    public static void main(String[] args)
    {
        test1();
    }

    /*
    局部变量的类型推断
    */
    public static void test1()
    {
        //1. 声明变量时，根据所附的值，推断变量的类型
        var num = 10;
        var list = new ArrayList<Integer>();
        list.add(123);

        //2. 遍历操作
        for (var i : list)
        {
            System.out.println(i);
            System.out.println(i.getClass());
        }

        //3.普通的遍历操作
        for (var i = 0; i < 100; i++)
        {
            System.out.println(i);
        }
    }

    public void test2()
    {
        //1. 局部变量不赋值，就不能实现类型推断
        //var num;

        //2. Lambda表示式中，左边的函数式接口不能声明为var
        Supplier<Double> sup = () -> Math.random();
//        var sup = () -> Math.random();

        //3.方法引用中，
        Consumer<String> con = System.out::println;
//        var con = System.out::println;

        //4.数组的静态初始化中
//        int[] arr = new int[]{1, 2, 3, 4};
//        var arr = {1, 2, 3, 4};

    }

    public static void test3()
    {
        //情况1:没有初始化的局部变量声明
//        var s = null;

        //情况6: catch块
//        try
//        {
//
//        } catch (var e)
//        {
//        }

    }

    //情况2:方法的返回类型
//    public static var method1()
//    {
//        return 0;
//    }

    //情况3:方法的参数类型
//    public static void method2(var num)
//    {
//
//    }

    //情况4:构造器的参数类型
//    public Java10Test(var i)
//    {
//
//    }

    //情况5:属性
//    var num = 0;

    public static void test4()
    {
        try
        {
            var url = new URL("http://atguigu.com");
            var connection = url.openConnection();
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
        }

    }

    //java1e的新特性:集合中新增的CopyOf(), 用于创建一个只读的集合
    public void test5()
    {
        //示例1:
        var list1 = List.of("Java", "Python", "C");
        var copy1 = List.copyOf(list1);
        System.out.println(list1 == copy1); // true

        //示例2:
        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);
        System.out.println(list2 == copy2); // false

        //示例1和2代码基本-致，为什么 一个为true, -一个为false?

    }


}