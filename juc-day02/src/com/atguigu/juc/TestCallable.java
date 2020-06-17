package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    一、创建执行线程的方式三：实现Callable接口,相较于实现Runnable接口的方式，方法可以有返回值，并且可以抛出异常。
    二、执行callable 方式，需要FutureTask 实现类的支持，用于接收运算结果。 FutureTask 是Future 接口的实现类

 */
public class TestCallable
{
    public static void main(String[] args)
    {
        ThreadDemo threadDemo = new ThreadDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(threadDemo);
        long l = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++)
        {
            new Thread(futureTask).start();
        }

        //接收运算的结果
        try
        {
            Integer integer = futureTask.get();     //FutureTask可用于闭锁
            long l1 = System.currentTimeMillis();
            System.out.println("耗费的时间是：" + (l1 - l));
            System.out.println(integer);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }


    }
}


class ThreadDemo implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception
    {
        synchronized (this)
        {
            int sum = 0;
            for (int i = 0; i <= 10; i++)
            {
                System.out.println(i);
                sum += i;
            }
            return sum;
        }

    }
}