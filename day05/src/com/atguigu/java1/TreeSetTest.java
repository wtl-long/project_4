package com.atguigu.java1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @create 2020-06-01 21:26
 **/
public class TreeSetTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    /*
        1.向TreeSet中添加的数据，要求是相同类的对象。
        2.两种排序方式:自然排序（Comparable）和定制排序（Comparator）
        3.自然排序中，比较两个对象是否相同的标准为: compareTg() 返回e.不再是equals().
        4.定制排序中，比较两个对象是否相同的标准为: compare() 返回e.不再是equals().
     */

    public static void test1()
    {
        TreeSet set = new TreeSet();

        //先败: 不能添加不同类的对象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom", 12));

        //举例一：
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        //举例二：
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 13));
        set.add(new User("Tom", 12));
        set.add(new User("A", 12));
        set.add(new User("B", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    public static void test2()
    {
        Comparator com = new Comparator()
        {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2)
            {
                if (o1 instanceof User && o2 instanceof User)
                {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                } else
                {
                    throw new RuntimeException("输入的数据类型不匹配");
                }

            }
        };

        TreeSet set = new TreeSet(com);

        set.add(new User("Tom", 13));
        set.add(new User("Tom", 12));
        set.add(new User("A", 12));
        set.add(new User("B", 12));

        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

    }
}
