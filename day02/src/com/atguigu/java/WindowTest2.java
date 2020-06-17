package com.atguigu.java;

/**
 * 使用同步代码块解决继承Thread方式的线程安全问题
 * 可以考虑使用当前类的class充当同步监视器
 *
 * 例子：创建3个窗口卖票，总票数为100张，继承Thread方法
 *
 *
 * @create 2020-05-29 13:10
 **/
public class WindowTest2
{
    public static void main(String[] args)
    {
        Window w1=new Window();
        Window w2=new Window();
        Window w3=new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }



}


class Window extends Thread
{
    private static int ticket=100;
    private static Object obj=new Object();

    @Override
    public void run()
    {
        while (true)
        {
            //synchronized(this)错误的方式，此处的this代表w1,w2,w3
            //可以使用synchronizde(Window.class)
            synchronized (obj)
            {
                if(ticket>0)
                {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName()+"卖票，票号为："+ticket);
                    ticket--;
                }
                else
                {
                    break;
                }
            }

        }
    }
}
