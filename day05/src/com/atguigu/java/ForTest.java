package com.atguigu.java;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK5.0新增了foreach循环，用于遍历数组和集合
 *
 * @create 2020-05-31 22:53
 **/
public class ForTest
{
    public static void main(String[] args)
    {
        test1();
    }

    //遍历集合
    public static void test1()
    {
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(new Person("Jerry", 20));
        coll1.add(456);
        coll1.add(false);
        coll1.add(new java.lang.String("java"));

        //for(集合元素的类型局部变量:集合对象)
        //内部仍然调用J迭代器。|
        for (Object o : coll1)
        {
            System.out.println(o);
        }

    }

    //练习题
    public static void test2()
    {
        String a[] = new String[]{"CC", "CC", "CC"};

        //正常赋值
        for (int i = 0; i < 3; i++)
        {
            a[i] = "GG";
        }

        //错误赋值
        for (String o : a)
        {
            o = "GG";
        }
    }

}
