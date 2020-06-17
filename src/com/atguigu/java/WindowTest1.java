package com.atguigu.java;

/**
 * 例子：创建3个窗口卖票，总票数为100张，实现Runnable接口方法
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

    @Override
    public void run()
    {
            while(true)
            {
                if(ticket>0)
                {
                    System.out.println(Thread.currentThread().getName()+"卖票，票号："+ticket);
                    ticket--;
                }
                else
                {
                    break;
                }
            }
    }
}