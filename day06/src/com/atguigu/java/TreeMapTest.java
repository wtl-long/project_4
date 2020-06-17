package com.atguigu.java;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * @create 2020-06-02 14:41
 **/
public class TreeMapTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }


    //向TreeMap中添加key-value，要求key必须是由同-一个类创建的对象
    //因为要按照key进行排序:自然排序、定制排序
    //自然排序
    public static void test1()
    {
        TreeMap map = new TreeMap();
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);
        User u5 = new User("Rose", 1);
        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);
        map.put(u5, 100);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    //定制排序
    public static void test2()
    {
        TreeMap map = new TreeMap(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                if (o1 instanceof User && o2 instanceof User)
                {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入的数据异常");
            }
        });
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);
        User u5 = new User("Rose", 1);
        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);
        map.put(u5, 100);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

}
