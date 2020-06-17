package com.atguigu.java1;


import java.util.Comparator;

/*
    Lambda表达式的使用举例
 */
public class LambdaTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    private static void test1()
    {
        Runnable a = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("我爱北京天安门");
            }
        };

        a.run();

        System.out.println("*************************");

        Runnable a1 = () -> System.out.println("我爱麻辣烫");
        a1.run();
    }

    public static void test2()
    {
        Comparator<Integer> objectComparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return Integer.compare(o1, o2);
            }

        };
        int compare = objectComparator.compare(1, 2);
        System.out.println(compare);


        //Lambda表达式的写法
        Comparator<Integer> objectComparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = objectComparator.compare(3, 2);
        System.out.println(compare2);

        //方法引用
        Comparator<Integer> objectComparator3 = Integer::compare;
        int compare3 = objectComparator.compare(3, 2);
        System.out.println(compare3);

    }


}
