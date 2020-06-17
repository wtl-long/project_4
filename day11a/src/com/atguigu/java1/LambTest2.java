package com.atguigu.java1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
    java内置的4.大核心函数式接口
    消费型接口Consumer<T>        void accept(T t)
    供给型接口Supplier<T>        T get()
    函数型接口Function<T, R>     R apply(T t)
    断定型接口Predicate<T>       boolean test(T t)

 */
public class LambTest2
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    public static void test1()
    {
        happyTime(600, new Consumer<Double>()
        {
            @Override
            public void accept(Double aDouble)
            {
                System.out.println("矿泉水的价格为：" + aDouble);
            }
        });

        System.out.println("******************");

        happyTime(500, aDouble -> System.out.println("矿泉水的价格为：" + aDouble));
    }

    public static void happyTime(double money, Consumer<Double> con)
    {
        con.accept(money);
    }

    public static void test2()
    {
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "西京", "普京");
        List<String> list1 = filterString(list, new Predicate<String>()
        {
            @Override
            public boolean test(String s)
            {
                return s.contains("京");
            }
        });
        System.out.println(list1);

        System.out.println("*****************************");
        List<String> list2 = filterString(list, s -> s.contains("京"));
        System.out.println(list2);
    }

    //根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public static List<String> filterString(List<String> list, Predicate<String> pre)
    {
        ArrayList<String> objects = new ArrayList<>();
        for (String s : list)
        {
            if (pre.test(s))
            {
                objects.add(s);
            }
        }
        return objects;
    }


}