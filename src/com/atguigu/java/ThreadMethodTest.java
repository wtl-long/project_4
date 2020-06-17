package com.atguigu.java;

/**
 * 测试Thread类的常用方法：
 * 1. start：启动当前线程
 * 2. run：线程要执行的操作
 * 3. currentThread：静态方法，返回当前代码执行的线程
 * 4. getName：获取当前线程的名字
 * 5. setName：设置当前线程的名字
 * 6. yield：释放当前cpu的执行权
 * 7. join：当前执行的线程中插入其他线程，直到其他线程执行结束，才会重启原来的线程
 * 8. stop：强制结束当前线程
 * 9. sleep（long millistime：毫秒）：让线程睡眠指定的时间（毫秒）
 * 10. isAlive（）：判断当前线程是否存活
 *
 *
 * 线程的优先级：
 * 1.
 * MAX_PRIORITY：10
 * MIN_PRIORITY：1
 * NORM_PRIORITY：5
 * 2. 如何设置和获取当前线程的优先级：
 *      getPriority()：获取线程的优先级
 *      setPriority(int p)：设置线程的优先级
 *
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
            HelloThread t1=new HelloThread("Thread:1");
//            t1.setName("线程1");

            //设置分线程的优先级
            t1.setPriority(Thread.MAX_PRIORITY);
            t1.start();

            //给主线程设置名字
            Thread.currentThread().setName("主线程");
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            for(int i=0;i<100;i++)
            {
                if(i % 2==0)
                {
                    System.out.println(Thread.currentThread().getName()+"："+Thread.currentThread().getPriority()+"："+i);
                }

//                if (i==20)
//                {
//                    try {
//                        t1.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }

        System.out.println(t1.isAlive());

    }
}

class HelloThread extends Thread
{
    @Override
    public void run()
    {
        for(int i=0;i<100;i++)
        {
            if(i % 2==0)
            {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"："+Thread.currentThread().getPriority()+"："+i);
            }

//            if (i % 20 == 0)
//            {
//                yield();
//            }
        }
    }

    //构造器给线程起名
    public HelloThread(String name)
    {
        super(name);
    }

}