package com.atguigu.java;

import java.util.*;

/*
    Collections :操作Collection、Map的工具类


    面试题: Collection 和Collections的区别?

 */
public class CollectionsTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    /*
        reverse(List):反转List中元素的顺序
        shuffLe(List):对List集合元素进行随机排序
        sort(List):根据元素的自然顺序对指定List集合元素按升序排序
        sort(List, Comparator): 根据指定的Comparator产生的顺序对List集合元素进行排序
        swap(List，int,int):将指定List集合中的i处元素和j处元素进行交换

        Object max(Collection):根据元素的自然顺序，返回给定集合中的最大元素
        Object max(Collection，Comparator): 根据Comparator指定的顺序，返回
        给定集合中的最大元素
        Object min(Collection)
        Object min(Collection, Comparator)
        int frequency(Collection, Object): 返回指定集合中指定元素的出现次数
        void copy(List dest,List src):将src中的 内容复制到dest中
        boolean replaceAll(List list，Object oldVal, Object newVal):使用新值替换
        List对象的所有旧值

     */

    public static void test2()
    {
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-765);
        List dest = new ArrayList();

// 报异常: IndexOutOfBoundsException("Source does not fit in dest")|
//
//        List dest = new ArrayList();
//        Collections.copy(dest,List);
        List<Object> objects = Arrays.asList(new Object[list.size()]);
        Collections.copy(objects, list);
        System.out.println(objects);

        /*
        collections类中提供了多个synchronizedXxx() 方法，
        该方法可使将指定集合包装成线程同步的集合，从而可以解决
        多线程并发访问集合时的线程安全问题
        */
        //返回的集合就是线程安全的
        List list1 = Collections.synchronizedList(list);


    }

    public static void test1()
    {
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-765);
        list.add(765);
        System.out.println(list);
//        Collections.reverse(list);
//        Collections.shuffle(list);
        //从小到大
        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                if (o1 instanceof Integer && o2 instanceof Integer)
                {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;
                    return -Integer.compare(i1, i2);

                }
                throw new RuntimeException("输入的数据格式异常");
            }
        });
        //从大到小
        System.out.println(list);
        //指定元素出现的频率
        System.out.println(Collections.frequency(list, 765));

    }
}
