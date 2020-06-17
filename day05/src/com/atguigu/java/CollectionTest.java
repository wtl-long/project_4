package com.atguigu.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中声明的方法的测试
 * <p>
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals().
 *
 * @create 2020-05-31 16:37
 **/
public class CollectionTest
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test1()
    {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Person("Jerry", 20));
        coll.add(456);
        coll.add(new java.lang.String("java"));
        coll.add(false);
        //contains(Object obj)
        System.out.println(coll.contains(132));
        System.out.println(coll.contains(123));
        //说明contains判断的是内容,如果重写了Object的equals（HashCode）方法
        // 则new的两个对象相同，返回true
        //String类自动实现了equals
        System.out.println(coll.contains(new String("java")));
        System.out.println(coll.contains(new Person("Jerry", 20)));

        //containsAll(Collection coll2)
        //方式一:ArrayList
//        Collection coll2 = new ArrayList();
//        coll2.add(123);
//        coll2.add(new Person("Jerry", 20));
//        coll2.add(456);
//        coll2.add(new java.lang.String("java"));
//        coll2.add(false);
//        System.out.println(coll.containsAll(coll2));
        //方式二:Arrays.asList
        Collection coll2 = Arrays.asList(456, new Person("Jerry", 20),
                123, new java.lang.String("java"), false);
        System.out.println(coll.containsAll(coll2));
    }

    public static void test2()
    {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Person("Jerry", 20));
        coll.add(456);
        coll.add(new java.lang.String("java"));
        coll.add(false);

        //remove(Object obj)
        coll.remove(123);
        System.out.println(coll);

        //自定义类重写equals方法则可以正常删除
        coll.remove(new Person("Jerry", 20));
        System.out.println(coll);

        //removeAll(Collection coll1):从当前集合中删除coll1中所有的元素
        Collection coll2 = Arrays.asList(456, new String("java"));
        coll.removeAll(coll2);
        System.out.println(coll);

    }

    public static void test3()
    {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Person("Jerry", 20));
        coll.add(456);
        coll.add(new java.lang.String("java"));
        coll.add(false);

//        Collection coll2 = Arrays.asList(new String("java"));
//
//        //retainAll(Collection coll2):交集
//        coll.retainAll(coll2);
//        System.out.println(coll);

        //equals(Object obj)
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(new Person("Jerry", 20));
        coll1.add(456);
        coll1.add(false);
        coll1.add(new java.lang.String("java"));

        //顺序不同返回false
        System.out.println(coll.equals(coll1));

    }


    public static void test4()
    {
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(new Person("Jerry", 20));
        coll1.add(456);
        coll1.add(false);
        coll1.add(new java.lang.String("java"));


        //hashCode
        System.out.println(coll1.hashCode());

        //集合--->数组:toArray
        Object[] objects = coll1.toArray();
        for (int i = 0; i < objects.length; i++)
        {
            System.out.println(objects[i]);
        }

        //拓展：数组--->集合
        List<String> strings = Arrays.asList(new String[]{"BBB", "AA", "CC"});
        System.out.println(strings);

        List<int[]> ints = Arrays.asList(new int[]{123, 456, 789});
        System.out.println(ints);       //[[I@4554617c]

        List int1 = Arrays.asList(123, 456, 789);
        System.out.println(int1);       //[123, 456, 798]

        List<Integer> integers = Arrays.asList(new Integer[]{123, 456, 798});
        System.out.println(integers);       //[123, 456, 798]

        //iterator：返回Iterator接口的实例，用于遍历集合元素，在Iteratortest.java中测试


    }
}
