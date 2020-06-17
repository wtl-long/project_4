package com.atguigu.exer;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2020-06-01 19:50
 **/
public class ListExer
{
    public static void main(String[] args)
    {
        test1();
    }


    public static void test1()
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add("java");
        arrayList.add(false);
        test2(arrayList);
        System.out.println(arrayList);
    }

    public static void test2(List list)
    {
        //根据索引删除
        list.remove(2);
        //根据对象删除
        list.remove(new Integer(123));
    }
}
