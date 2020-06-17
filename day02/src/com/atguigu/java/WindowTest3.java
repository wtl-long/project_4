package com.atguigu.java;

/**
 * 使用同步方法解决实现Runnable的线程安全问题
 *
 * 关于同步方法的说明：
 * 1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
 * 2. 非静态的同步方法，同步监视器是：this
 *    静态的同步方法，同步监视器是：类.class
 * @create 2020-05-29 14:56
 **/
public class WindowTest3
{
    public static void main(String[] args)
    {
        Win3 w1 = new Win3();

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


class Win3 implements Runnable
{
    private int ticket=100;
    Object obj=new Object();

    @Override
    public  void  run()
    {
        while(true)
        {
            show();
            if(ticket<=0)
            {
                break;
            }
        }
    }

    private synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "卖票，票号：" + ticket);
            ticket--;
        }
    }

}
