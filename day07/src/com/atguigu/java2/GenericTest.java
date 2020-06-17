package com.atguigu.java2;

/*
    1.泛型在继承方面的体现


    2.通配符的使用

 */


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericTest
{
    public static void main(String[] args)
    {
//        test1();
        test3();
    }

    /*
        1.泛型在继承方面的体现
        类A是类B的父类，G<A> 和G<B>二者不具备子父类关系，二者是并列关系。

        补充:类A是类B的父类，A<G>是B<G>的父类

     */
    private static void test1()
    {
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //不允许,此时的list1和list2的类型不具备继承关系
//        List<object> list1 = null;
//        List<String> list2 = nu1l;
//        list1 =list2;
        //相当于
//        Date date = new Date();
//        str = date;
        /*
        反证法:
        假设List1 = list2;
        List1. add(123);导致混入非String的数据。出错。
        */
    }

    public static void test2()
    {
        //父类
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;
        list1 = list3;
        list2 = list3;

    }

    /*
        通配符的使用
        通配符：?
        类A是类B的父类， G<A>和G<B> 是没有关系的，. 二者共同的父类是: G<?>
     */
    public static void test3()
    {
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;
        list = list1;
        list = list2;

//        print(list1);
//        print(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加:对于List<?> 就不能向其内部添加数据。
        //除了添加null之外
        //        list.add("DD");
        //        list.add('?');
        list.add(null);

        //获取(读取):允许读取数据，读取的数据类型为0bject。
        System.out.println(list.get(0));
    }

    public static void print(List<?> list)
    {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext())
        {
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    /*
    3.有限制条件的通配符的使用
        ? extends A:
             G<? extends A>可以作为G<A> 和G<B>的父类的，其中B是A的子类
        ? super A:
             G<? super A>可以作为G<A>和G<B>的父类的，其中B是A的父类

    */
    public static void test4()
    {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;
        List<Student> list3 = null;
        List<Person> list4 = null;
        List<Object> list5 = null;

        list1 = list3;
        list1 = list4;
//        list1=list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据:
        //返回值取最大的类
        list1 = list4;
        Person p = list1.get(0);
        Object p1 = list1.get(0);
//编译不通过
//        Student S = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
//        Person obj = list2.get(0);

        //存入数据
        //编译不通过
//        list1.add(new Student());

        list2.add(new Student());
        list2.add(new Person());
    }


}
