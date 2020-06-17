package com.atguigu.java4;

import java.util.Optional;

/*
    Optional类: 为了在程序中避免出现空指针异常而创建的。
    常用的方法:
        ofNulLabLe(T t)
        orELse(T t)

 */
public class OptionalTest
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /*
        Optional.of(T t) :创建- 一个Optional 实例，t必须非空;
        Optional. emipty() :创建一个空的Optional实例
        Optional. ofNuLLable(T t): t可以为null
    */
    public static void test1()
    {
        Girl girl = new Girl();
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1);

    }

    public static void test2()
    {
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);

        //orELse(T t1): 如果单前的Optional内部封装的t是非空的，则返回内部的t.
        //如果内部的t是空的，则返回orELse()方法 中的参数t1
        Girl girl2 = girl1.orElse(new Girl("女孩"));
        System.out.println(girl2);
    }

    public static String getGirlName(Boy boy)
    {
        return boy.getGirl().getName();
    }

    public static void test3()
    {
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    //优化以后的方法
    public static String getGirlName2(Boy boy)
    {
        if (boy != null)
        {
            Girl girl = boy.getGirl();
            if (girl != null)
            {
                return girl.getName();
            }
        }
        return null;
    }

    public static void test4()
    {
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName2(boy);
        System.out.println(girlName);

    }

    //使用Optional方法后的getGirlName()
    public static String getGirlName3(Boy boy)
    {
        Optional<Boy> boy1 = Optional.ofNullable(boy);
        Boy boy2 = boy1.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy2.getGirl();
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        Girl girl2 = girl1.orElse(new Girl("桂林阿扎"));
        return girl2.getName();
    }

    public static void test5()
    {
        Boy boy = new Boy();
//        boy = null;     迪丽热巴
//        String girlName3 = getGirlName3(boy);       桂林阿扎
        Boy boy1 = new Boy(new Girl("安妮"));
        String girlName3 = getGirlName3(boy1);  //安妮
        System.out.println(girlName3);
    }

}
