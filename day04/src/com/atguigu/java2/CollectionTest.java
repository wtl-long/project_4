package com.atguigu.java2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 一、集合框架的概述
 * <p>
 * 1. 集合、数组都是对多个数据进行存储操作的结构，简称Java容器
 * 说明：此时的存储说的都是内存层面的，不涉及到持久化（磁盘）的存储
 * <p>
 * 2.1 数组在存储多个数据方面的特点：
 * >一旦初始化以后，其长度就确定了
 * >元素类型也确定
 * 2.2 数组在存储多个数据方面的缺点：
 * >长度不可修改
 * >数组中的方法有限，对于添加、删除、插入数据等操作，非常不便
 * >获取数组中实际元素的个数，数组没有方法可用
 * >数组存储数据的特点：有序、可重复。对于无序、不重复的需求，不能满足
 * <p>
 * 二、集合框架
 * |--- Collection接口：单列集合，用来存储一个一个的对象
 * |---List接口:存储有序的、可重复的数据   --->"动态数组"
 * |---ArrayList、LinkedList、Vector
 * |---Set接口：存储无序的、不可重复的数据  --->"高中讲的集合"
 * |---HashSet、LinkedHashSet、TreeSet
 * <p>
 * |---Map接口：双列集合，用来存储一对一对（key - value）的数据   --->高中函数：y=f(x)
 * |---HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 * <p>
 * 三、Collection接口方法的使用
 *
 * @create 2020-05-31 15:24
 **/
public class CollectionTest
{
    public static void main(String[] args)
    {
        test1();
    }

    public static void test1()
    {
        Collection coll = new ArrayList();

        //add(Object e)
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        //size()
        System.out.println(coll.size());

        //addAll
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("abc");
        coll.addAll(coll1);

        System.out.println(coll.size());
        System.out.println(coll);

        //clear
        coll.clear();

        //isEmpty
        System.out.println(coll.isEmpty());

    }

}
