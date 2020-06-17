package com.atguigu.exer;

/**
 * 练习：创建两个线程，一个输出100以内的偶数，一个输出100以内的奇数
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThread1 t1=new MyThread1();
//        MyThread2 t2=new MyThread2();
//
//        t1.start();
//        t2.start();

        //创建Thread匿名子类方式
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "：" + i);
                    }
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + "：" + i);
                    }
                }
            }
        }.start();

    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            if(i % 2==0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            if(i % 2!=0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}
