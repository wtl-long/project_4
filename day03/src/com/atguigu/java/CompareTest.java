package com.atguigu.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一、说明：Java中的对象，正常情况下，只能进行比较：== != 不能使用> <，
 * 但是在开发中需要对多个对象进行排序，言外之意需要比较对象的大小。
 * 如何实现？使用两个接口中的任何一个:Comparable、Comparator
 *
 * 二、Comparable接口与Comparator的使用的对比：
 * Comparable接口可以使自定义类在任何位置都可以比较大小
 * Comparator属于临时性的比较
 * <p>
 * 二、Comparable接口的使用
 *
 * @create 2020-05-30 14:51
 **/
public class CompareTest
{

    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }


    /*
        Comparable接口的使用举例：自然排序
        1. 像String、包装类等实现了Comparable接口，重写了compareTo方法，给出了比较两个对象大小的方法
        2. 像String、包装类重写compareTo方法以后，进行了从小到大的排列
        3. 重写CompareTo(obj)的规则：
                    如果当前对象this大于形参对象obj,则返回正整数,
                    如果当前对象this小于形参对象obj,则返回负整数，
                    如果当前对象this等于形参对象obj,则返回零。
        4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo（obj）方法
            在compareTo方法中指明如何排序
     */
    public static void test1()
    {
        String[] strings = {"ZZ", "WW", "AA", "DD", "EE"};
        Arrays.sort(strings);

        System.out.println(Arrays.toString(strings));

        int a[] = {3, 5, 1, 4, 2};
        Arrays.sort(a);

        System.out.println(Arrays.toString(a));

    }

    public static void test2()
    {
        Goods goods[] = new Goods[4];
        goods[0] = new Goods("dell", 10);
        goods[1] = new Goods("huawei", 30);
        goods[2] = new Goods("lianx", 20);
        goods[3] = new Goods("huipu", 1);

        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));
    }


    /*
        Comparator接口的使用：定制排序
        1.背景:
        当元素的类型没有实现java. Lang. Comparable接口而又不方便修改代码,
        或者实现了java. Lang. Comparable接口的排序规则不适合当前的操作，
        |那么可绊考虑使用Comparator的对象来排序
        2.重写compare(Object o1,0bject o2)方法，比较o1 和o2的大小:
        如果方法返回正整数，则表示d1大于o2;如果返回0，表示相等;
        返回负整数，表示o1小于o2。
     */
    public static void test3()
    {
        String[] strings = {"ZZ", "WW", "AA", "DD", "EE"};
        Arrays.sort(strings, new Comparator()
        {
            //按照字符串从大到小的顺序排列
            @Override
            public int compare(Object o1, Object o2)
            {
                if (o1 instanceof String && o2 instanceof String)
                {
                    String str1 = (String) o1;
                    String str2 = (String) o2;
                    return -str1.compareTo(str2);

                }
//                return 0;
                throw new RuntimeException("输入的数据类型不一致!");
            }
        });
        System.out.println(Arrays.toString(strings));

    }


    private static void test4()
    {
        Goods goods[] = new Goods[4];
        goods[0] = new Goods("dell", 10);
        goods[1] = new Goods("huawei", 30);
        goods[2] = new Goods("lianx", 20);
        goods[3] = new Goods("huipu", 1);

        Arrays.sort(goods, new Comparator()
        {
            //先按照产品名称从低到高，再按照价格从高到低
            @Override
            public int compare(Object o1, Object o2)
            {
                if (o1 instanceof Goods && o2 instanceof Goods)
                {
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;

                    if (g1.getName().equals(g2.getName()))
                    {
                        return -Double.compare(g1.getPrice(), g2.getPrice());
                    } else
                    {
                        return g1.getName().compareTo(g2.getName());
                    }
                }
//                return 0;
                throw new RuntimeException("输入的数据类型不一致!");
            }
        });
        System.out.println(Arrays.toString(goods));
    }

}