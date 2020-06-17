package com.atguigu.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
    动态代理的举例
 */
public class ProxyTest
{
    public static void main(String[] args)
    {
        SuperMan superMan = new SuperMan();
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("********************");
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);

        proxyInstance1.produceCloth();

    }

}


interface Human
{
    String getBelief();

    void eat(String food);

}

//被代理类
class SuperMan implements Human
{

    @Override
    public String getBelief()
    {
        return "I believe I can fly";

    }

    @Override
    public void eat(String food)
    {
        System.out.println("我喜欢吃" + food);
    }
}


class ProxyFactory
{
    //调用此方法，返回一个代理类的对象。解决问题一
    public static Object getProxyInstance(Object obj)   //obj:被代理类对象
    {
        MyInvocationHandlerl myInvocationHandlerl = new MyInvocationHandlerl();
        myInvocationHandlerl.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandlerl);
    }

}

class MyInvocationHandlerl implements InvocationHandler
{
    private Object obj;// 需要使用被代理类的对象进行赋值

    public void bind(Object obj)
    {
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时， 就会自动的调用如下的方法: invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象
        Object invoke = method.invoke(obj, args);
        return invoke;

    }
}