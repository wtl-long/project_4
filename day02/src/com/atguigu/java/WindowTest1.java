package com.atguigu.java;

/**
 * 例子：创建3个窗口卖票，总票数为100张，实现Runnable接口方法
 *
 * 1. 在java中通过同步机制，来解决线程的安全问题
 *
 * 方式一：同步代码块
 *  synchronized(同步监视器)
 *  {
 *      同步代码
 *  }
 *  说明：1. 操作共享数据块的代码，即为同步代码
 *       2. 共享数据：多个线程共同操作的变量。比如：ticket
 *       3. 同步监视器（锁）：任何一个类的对象，都可以充当锁
 *          要求：多个线程共用同一把锁
 *
 *       补充：Runnable接口实现线程的方法可以使用this充当同步监视器
 *
 * 方式二：同步方法
 *      如果操作共享数据的代码刚好包含在一个方法中，则可以声明此方法为同步方法
 *
 *
 * 2. 同步的方式，解决了线程的安全问题。 ---优点
 *    只能有一个线程参与，相当于单线程的过程，效率低。 ---缺点
 *
 * @create 2020-05-29 13:40
 **/
public class WindowTest1
{
    public static void main(String[] args)
    {
        Win w1 = new Win();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}


class Win implements Runnable
{
    private int ticket=100;
    Object obj=new Object();

    @Override
    public void run()
    {
            while(true)
            {
//                synchronized (obj)
//                {

                //此时this代表唯一的Win对象w1
                 synchronized (this)
                 {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + "卖票，票号：" + ticket);
                        ticket--;
                    } else {
                        break;
                    }
                }
            }
    }
}