package com.atguigu.juc1;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    1. ReadWriteLock :读写锁
    写写、读写需要“互斥”
    读读不需要互斥

 */
public class TestReadWriteLock
{
    public static void main(String[] args)
    {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                readWriteLockDemo.set((int) (Math.random() * 101));
            }
        }, "Write:").start();

        for (int i = 0; i < 100; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    readWriteLockDemo.get();
                }
            }).start();
        }

    }
}


class ReadWriteLockDemo
{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get()
    {
        lock.readLock().lock();     //上锁
        try
        {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally
        {
            lock.readLock().unlock();      //释放锁
        }
    }

    //写
    public void set(int number)
    {
        lock.writeLock().lock();

        try
        {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        } finally
        {
            lock.writeLock().unlock();
        }
    }

}