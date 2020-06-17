package com.atguigu.exer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @create 2020-06-01 21:56
 **/
public class ListTest
{
    //练习:在List内去除重复数字值，要求尽量简单(转化成Set)
    public static List duplicateList(List list)
    {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }

    public static void test1()
    {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        set.add(p1);
        set.add(p2);
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);
        set.add(new Person(1001, "CC"));
        System.out.println(set);
        set.add(new Person(1001, "AA"));
        System.out.println(set);

    }

    public static void main(String[] args)
    {
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2)
        {
            System.out.println(integer);
        }
        test1();
    }

}
