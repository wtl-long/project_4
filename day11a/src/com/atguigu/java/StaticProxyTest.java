package com.atguigu.java;

/*
    静态代理举例
    特点:代理类和被代理类在编译期间，就确定下来了。

 */

public class StaticProxyTest
{
    public static void main(String[] args)
    {
        //创建被代理对象
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //创建代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
        proxyClothFactory.produceCloth();

    }
}


interface ClothFactory
{
    void produceCloth();
}


//代理类
class ProxyClothFactory implements ClothFactory
{
    //用被代理对象进行实例化
    private ClothFactory cloth;

    public ProxyClothFactory(ClothFactory cloth)
    {
        this.cloth = cloth;
    }

    @Override
    public void produceCloth()
    {
        System.out.println("代理工厂做一下准备工作");

        cloth.produceCloth();

        System.out.println("代理工厂做收尾工作");
    }
}


//被代理类
class NikeClothFactory implements ClothFactory
{

    @Override
    public void produceCloth()
    {
        System.out.println("Nike工厂生产一批运动服");

    }
}