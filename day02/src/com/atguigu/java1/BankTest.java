package com.atguigu.java1;

/**
 * 使用同步机制将单例模式中的懒汉式（只能创建一个类的对象）改写为线程安全的
 *
 * @create 2020-05-29 18:51
 **/
public class BankTest {

}


class Bank
{
    private static Bank instance=null;

    private Bank(){}
    public static Bank getInstance()
    {
        //方式一：效率差
//        synchronized (Bank.class) {
//            if(instance==null)
//            {
//                instance=new Bank();
//            }
//            return instance;
//        }

        //方式二：效率高
        if (instance==null)
        {
            synchronized (Bank.class) {
                if(instance==null)
                {
                    instance=new Bank();
                }
            }
        }
        return instance;
    }
}
