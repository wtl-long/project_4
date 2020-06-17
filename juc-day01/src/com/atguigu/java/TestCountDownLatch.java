package com.atguigu.java;

import java.util.concurrent.CountDownLatch;

/*
    CountDownLatch : 闭锁，在完成某些运算是,只有其他所有线程的运算全部完成，当前运算才继续执行

 */
public class TestCountDownLatch
{
    public static void main(String[] args)
    {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++)
        {
            new Thread(latchDemo).start();
        }

        try
        {
            countDownLatch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));

    }

}


class LatchDemo implements Runnable
{
    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch)
    {
        this.latch = latch;
    }

    @Override
    public void run()
    {
        synchronized (this)
        {
            try
            {
                for (int i = 0; i < 50000; i++)
                {
                    if (i % 2 == 0)
                    {
                        System.out.println(i);
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                latch.countDown();
            }
        }


    }
}