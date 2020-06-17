package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全的方式三：Lock锁  ----jdk5新增
 *
 * 1. 面试题：synchronized与lock的异同？
 *  synchronized自动释放同步监视器
 *
 * @create 2020-05-29 22:33
 **/
public class LockTest
{
    public static void main(String[] args)
    {
        Window w=new Window();

        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}


class Window implements Runnable
{
    private int ticket=100;

    //1.实例化ReentrantLock
    private ReentrantLock lock=new ReentrantLock();

    @Override
    public void run()
    {
        while (true)
        {
            try {

                //2.调用lock
                lock.lock();

                if (ticket>0)
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }
                else
                {
                    break;
                }
            } finally {
                //3.解锁
                lock.unlock();

            }
        }
    }
}