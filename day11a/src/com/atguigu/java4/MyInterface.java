package com.atguigu.java4;

public interface MyInterface
{
    //默认是public权限
    void methodAbstract();

    //默认是public权限
    static void methodStatic()
    {
        System.out.println("我是接口中的静态方法");
    }

    //默认是public权限，这里的default是关键字
    default void methodDefault()
    {
        System.out.println("我是接口中的default方法");
    }

    private void methodPrivate()
    {
        System.out.println("我是接口中的私有方法");

    }
}
