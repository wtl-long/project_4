package com.atguigu.java;

/*
    如何自定义泛型结构:泛型类、泛型接口;泛型方法。

     1.关于自定义泛型类、泛型接口:

 */

import java.util.List;

public class GenericTest1
{
    public static void main(String[] args)
    {
//        test1();
        test3();
    }

    //测试泛型方法
    private static void test3()
    {
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        List<Integer> integers = order.copyFromArrayToList(arr);
        System.out.println(integers);

    }

    public static void test1()
    {
        //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为object类型
        //要求:如果大家定义了类是帶泛型的，建议在实例化时要指明类的泛型。

        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        //建议:实例化时指明类的泛型
        Order<String> stringOrder = new Order<>();
        stringOrder.setOrderT("order");

    }

    public static void test2()
    {
        //由于子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
        SubOrder subOrder = new SubOrder();
        subOrder.setOrderT(11);

        SubOrder1<String> stringSubOrder1 = new SubOrder1<>();
        stringSubOrder1.setOrderT("java");
    }

}
