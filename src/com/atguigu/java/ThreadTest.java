package com.atguigu.java;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run方法--->将此线程执行的操作声明在run中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start方法
 * <p>
 * 例子：遍历100以内所有的偶数
 */

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}


public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        //调用线程
        t1.start();
        //调用方法
        t1.run();

        //问题二：再启动一个线程，遍历100以内的偶数，不可以让已经start的线程去执行，会报illegalThreadsException
        //需要重新创建一个线程对象
        MyThread t2 = new MyThread();
        t2.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }

    }

}
