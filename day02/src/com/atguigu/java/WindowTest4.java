package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的线程安全
 *
 * @create 2020-05-29 18:31
 **/
public class WindowTest4
{
    public static void main(String[] args)
    {
        Window4 w1=new Window4();
        Window4 w2=new Window4();
        Window4 w3=new Window4();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }



}


class Window4 extends Thread
{
    private static int ticket=100;
    private static Object obj=new Object();

    @Override
    public void run()
    {
        while (true)
        {
            show();

            if (ticket<=0)
            {
                break;
            }
        }
    }

    //private synchronized void show()    //同步监视器this---->w1,w2,w3  错误
    //同步监视器Window4.class
    private static synchronized void show()
    {
        if(ticket>0)
        {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖票，票号为："+ticket);
            ticket--;
        }
    }

}