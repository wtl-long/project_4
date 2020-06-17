package com.atguigu.juc1;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @create 2020-06-10 18:59
 **/
public class TestScheduledThreadPool
{
    public static void main(String[] args) throws Exception
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++)
        {
            ScheduledFuture<Object> schedule = scheduledExecutorService.schedule(new Callable<Object>()
            {

                @Override
                public Object call() throws Exception
                {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 2, TimeUnit.SECONDS);
            System.out.println(schedule.get());
        }
        scheduledExecutorService.shutdown();

    }


}
