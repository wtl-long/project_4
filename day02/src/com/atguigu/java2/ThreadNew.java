package com.atguigu.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable接口的方式   ---JDK5.0新增
 *
 * 如何理解Callable接口比Runnable功能强大？
 * 1. call可以有返回值
 * 2. call可以抛出异常，被外面的操作捕获，获取异常信息
 * 3. Callable支持泛型
 *
 * @create 2020-05-30 9:54
 **/
public class ThreadNew
{
    public static void main(String[] args)
    {
        //3. 创建实现类的对象
        NumThread numThread = new NumThread();
        //4. 将此实现类的对象送给FutureTask构造器
        FutureTask<Integer> futureTask = new FutureTask<>(numThread);
        //5. 将FutureTask对象传到Thread构造器中
        new Thread(futureTask).start();

        try {
            //6. 获取Callable中call方法的返回值
            //get方法的返回值即为FutureTask构造器Callable参数的call方法的返回值
            Integer sum = futureTask.get();
            System.out.println("总合为："+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}


//1. 创建一个实现Runnable接口的实现类
class NumThread implements Callable<Integer>
{

    //2. 实现call方法，书写线程要执行的操作
    @Override
    public Integer call() throws Exception
    {
        int sum=0;

        for (int i = 0; i <= 100; i++)
        {
            if (i%2==0)
            {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }

}