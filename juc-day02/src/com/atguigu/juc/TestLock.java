package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    一、用于解决多线程安全问题的方式:
    synchronized:隐式锁
    1.同步代码块

    2.同步方法

    jdk 1.5后:
    3.同步锁Lock
    注意:是一个显示锁,需要通过1ock()方法上锁,必须通过un1ock()方法进行释放锁

 */
public class TestLock
{
    public static void main(String[] args)
    {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}


class Ticket implements Runnable
{

    private int ticket = 500;
    private Lock lock = new ReentrantLock();

    @Override
    public void run()
    {
        lock.lock();
        try
        {
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            while (ticket > 0)
            {
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticket);
            }
        } finally
        {
            lock.unlock();
        }
    }
}