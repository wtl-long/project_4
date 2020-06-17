package com.atguigu.java2;

/**
 * 线程通信的应用：生产者、消费者问题
 *
 * @create 2020-05-30 9:21
 **/
public class ProductTest
{
    public static void main(String[] args)
    {
        Clerk clerk=new Clerk();

        Producer pro=new Producer(clerk);
        pro.setName("生产者1");

        Consumer con=new Consumer(clerk);
        con.setName("消费者1");

        Consumer con2=new Consumer(clerk);
        con2.setName("消费者2");

        pro.start();
        con.start();
        con2.start();
    }
}

class Clerk
{
    private int productCount=0;

    //生产产品
    public synchronized void produceProduct()
    {
        if (productCount<20)
        {
            productCount++;

            //生产一个产品就可以去唤醒消费者
            notify();

            System.out.println(Thread.currentThread().getName()+":开始生产第"+productCount+"个产品");
        }
        else
        {
            //等待
            try
            {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //消费产品
    public synchronized void consumeProduct()
    {
        if (productCount>0)
        {
            System.out.println(Thread.currentThread().getName()+":开始消费第"+productCount+"个产品");
            productCount--;

            //消费一个产品就可以唤醒生产者
//            notify();
        }
        else
        {
            //等待
            try
            {
                //无产品开始生产
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread   //生产者
{
    private Clerk clerk;

    Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName()+"生产中……");

        while (true)
        {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }

    }
}

class Consumer extends Thread   //消费者
{
    private Clerk clerk;

    Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName()+"消费中……");

        while (true)
        {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}