package com.atguigu.java;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现run方法
 * 3. 创建实现类对象
 * 4. 将此对象作为参数传递到Thread类的构造器中创建Thread对象
 * 5. 通过Thread类对象调用start方法
 *
 *
 *
 * 比较创建线程的两种方式：
 * 开发中优先选择：实现Runnable接口的方式
 * 原因：1. 实现没有类的单继承的局限性
 *      2. 实现的方式更适合来处理多个线程有共享数据的情况
 *
 *
 * @create 2020-05-29 13:26
 **/
public class ThreadTest1
{
    public static void main(String[] args)
    {
        MThread mThread = new MThread();

        Thread t1 = new Thread(mThread);
        t1.start();

        //再启动一个线程，遍历100以内的偶数
        Thread t2 = new Thread(mThread);
        t2.start();
    }

}


class  MThread implements Runnable
{

    @Override
    public void run()
    {
        for (int i = 0; i < 100 ; i++)
        {
            if (i % 2 ==0)
            {
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }

    }
}


