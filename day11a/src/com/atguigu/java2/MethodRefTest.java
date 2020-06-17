package com.atguigu.java2;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
    方法引用的使用
    1.使用情境:当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用!

    2.方法引用，本质上就是Lambda表达式， 而Lambda表达式作为函数式接口的实例。所以
    方法引用，也是函数式接口的实例。

    3.使用格式: 类(或对象) :: 方法名

    4.具体分为如下的三种情况:
       情况1 对象::非静态方法
       情况2 类::静态方法
       情况3 类::非静态方法

    5.方法引用使用的要求:要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
    形参列表和返回值类型相同!（针对情况1和情况2）

 */
public class MethodRefTest
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

    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    public static void test1()
    {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");

        System.out.println("************************");

        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("beijing");

    }

    //Supplier中的T get()
    //Employee中的String getName()
    public static void test2()
    {
        Employee emp = new Employee(1001, "Tom", 23, 5600);
        Supplier<String> sup1 = () -> emp.getName();
        System.out.println(sup1.get());

        System.out.println("***************************");

        //意思为：Supplier中的T get()方法中的内容为emp对象中的getName
        Supplier<String> sup = emp::getName;
        System.out.println(sup.get());

    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    public static void test3()
    {
        Comparator<Integer> com = (t1, t2) -> Integer.compare(t1, t2);
        int compare = com.compare(50, 10);
        System.out.println(compare);

        System.out.println("****************************");

        Comparator<Integer> com1 = Integer::compare;
        int compare1 = com1.compare(50, 100);
        System.out.println(compare1);

    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    public static void test4()
    {
        Function<Double, Long> fun = d -> Math.round(d);
        System.out.println(fun.apply(5.5));

        Function<Double, Long> fun2 = Math::round;
        System.out.println(fun2.apply(12.2));

    }

    // 情况三：类 :: 实例方法（有难度）
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    public static void test5()
    {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        int compare = com1.compare("ec", "ed");
        System.out.println(compare);

        Comparator<String> com2 = String::compareTo;
        int compare1 = com2.compare("ec", "ed");
        System.out.println(compare1);

    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    public static void test6()
    {
        BiPredicate<String, String> b = (t1, t2) -> t1.equals(t2);
        System.out.println(b.test("ac", "ag"));

        System.out.println("*************************");

        BiPredicate<String, String> b1 = String::equals;
        System.out.println(b1.test("ad", "ad"));

    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    public static void test7()
    {
        Employee employee = new Employee(1001, "Jerry", 23, 6000);
        Function<Employee, String> fun = e -> e.getName();
        System.out.println(fun.apply(employee));

        System.out.println("**********************");

        Function<Employee, String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee));
    }

}
