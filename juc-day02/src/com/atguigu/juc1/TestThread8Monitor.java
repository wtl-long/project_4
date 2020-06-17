package com.atguigu.juc1;

/*
    题目：判断打印的是“one” or “two”

    1.两个普通同步方法，两个线程，标准打印，打印? //one two
    2.新增Thread.sleep()给getOne() ,打印? //one two
    3.新增普通方法getThree()，打印? //three one two
    4.两个普通同步方法，两个Number对象,打印? //two one
    5.


 */
public class TestThread8Monitor
{
    public static void main(String[] args)
    {
        Number number = new Number();
//        Number number1 = new Number();


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                number.getOne();
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                number.getTwo();
//                number1.getTwo();
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                number.getThree();
            }
        }).start();

    }
}

class Number
{
    public static synchronized void getOne()
    {
        System.out.println("One");
    }

    public synchronized void getTwo()
    {
        System.out.println("Two");
    }

    public void getThree()
    {
        System.out.println("three");
    }
}
