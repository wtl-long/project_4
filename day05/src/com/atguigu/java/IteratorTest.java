package com.atguigu.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合的遍历操作，使用迭代器Iterator接口
 * <p>
 * 集合元素的遍历操作，使用迭代器Iterator接口
 * 1.内部的方法: hasNext() 和next()
 * 2. 集合对象每次调用iterator()方法都得到一个全新的迭代器对象,
 * 默认游标都在集合的第一 个元素之前。
 * 3.内部定义了remove(),可以在遍历的时候，删除集合中的元素。此方法不同于集合直接调用remove方法
 *
 * @create 2020-05-31 22:31
 **/
public class IteratorTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    public static void test1()
    {
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(new Person("Jerry", 20));
        coll1.add(456);
        coll1.add(false);
        coll1.add(new java.lang.String("java"));

        //hasNext():判断是否还有下一个元素
        //next():①指针下移②将下移以后集合位置上的元素返回

        Iterator iterator = coll1.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    //remove
    //测试Iterator中的remove()I
    //如果还未调用next()或在上一次调用next方法之后已经调用了remove 方法,
    //再调用remove都会报ILlegalStateException。

    public static void test2()
    {
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(456);
        coll1.add(false);
        coll1.add(new Person("Jerry", 20));
        coll1.add(new java.lang.String("java"));

        Iterator iterator = coll1.iterator();
        while (iterator.hasNext())
        {
            Object next = iterator.next();
            if (next.equals(456))
            {
                iterator.remove();
            }
        }

        //指针移动所以要重写生成
        iterator = coll1.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

    }
}
