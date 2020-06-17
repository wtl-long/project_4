package com.atguigu.java2;

/**
 * 线程通信的例子：打印 1-100，使用两个线程交替打印
 *
 * 涉及到的三个方法：
 *      wait()：执行此方法，当前线程进入阻塞状态，并释放同步监视器
 *      notify()：执行此方法会唤醒一个被wait的方法，如果有多个线程被wait，则唤醒优先级最高的
 *      notifyAll()：执行此方法会唤醒所有被wait的方法
 *
 * 说明：
 *      1. wait()、notify()、notifyAll()三个方法必须使用在同步代码块或同步方法中（Lock中不行）
 *      2. wait()、notify()、notifyAll()三个方法的调用者必须是同步代码块或同步方法中的同步监视器,
 *          否则会报IllegalMonitorStateException错误
 *      3. wait()、notify()、notifyAll()三个方法是定义在java.lang.Object中
 *
 * 面试题：sleep 和 wait的异同？
 * 1. 相同点：都可以阻塞当前线程
 * 2. 不同点：1）两个方法声明的位置不同：Thread类中声明sleep，Object类中声明wait
 *           2）调用的范围不同：sleep在任何场景中使用，wait在同步代码块或同步方法中使用
 *           3）关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep不会是否锁，wait会
 * @create 2020-05-29 23:26
 **/
public class CommunicationTest
{
    public static void main(String[] args)
    {
        Number n=new Number();

        Thread t1=new Thread(n);
        Thread t2=new Thread(n);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }

}

class Number implements Runnable
{
    private int number=1;
    Object obj=new Object();

    @Override
    public void run()
    {
        while (true)
        {
            synchronized (obj) {

                obj.notify();

                if (number<=100)
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"："+number);
                    number++;

                    try {
                        //调用wait使线程进入阻塞状态
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    break;
                }
            }
        }
    }
}
