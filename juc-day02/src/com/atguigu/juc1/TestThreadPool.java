package com.atguigu.juc1;

/*
    一、线程池:提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁线程的额外开销，提高了响应速度

    二、线程池的体系结构:
    java. util . concurrent. Executor :负责线程的使用与调度的根接口
    |--ExecutorService 子接口:线程池的主要接口
        |--ThreadPoolExecutor 线程池的实现类
        |--ScheduledExecutorService 子接口:负责线程的调度
            |--ScheduledThreadPoolExecutor:继承ThreadPoolExecutor,实现了ScheduledThreadPoolExecutor

    三、工具类: Executors
    ExecutorService newFixedThreadPool() :创建固定大小的线程池
    ExecutorService newCachedThreadPool() :缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量
    ExecutorService newSingleThreadExecutor() :创建单个线程池。线程池中只有一个线程

    ScheduledExecutorService newScheduledThreadPoo1() :创建固定大小的线程,可以延迟或定时的执行任务

 */


import java.util.ArrayList;
import java.util.concurrent.*;

public class TestThreadPool
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        //1. 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(5);

        ArrayList<Future<Integer>> objects = new ArrayList<>();


        for (int i = 0; i < 10; i++)
        {
            Future<Integer> submit = service.submit(new Callable<Integer>()
            {
                @Override
                public Integer call() throws Exception
                {
                    int sum = 0;

                    for (int i = 0; i < 100; i++)
                    {
                        sum += i;
                    }

                    return sum;
                }
            });
            objects.add(submit);
        }


        service.shutdown();

        for (Future<Integer> future : objects)
        {
            System.out.println(future.get());
        }


//        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
//
//        //2. 为线程池中的线程分配任务
//        for (int i = 0; i < 10; i++)
//        {
//            service.submit(threadPoolDemo);
//        }
//
//
//        //3. 关闭线程池
//        service.shutdown();
    }
}

class ThreadPoolDemo implements Runnable
{
    private int i = 0;

    @Override
    public void run()
    {
        while (i < 100)
        {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}