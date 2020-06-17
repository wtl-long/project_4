package com.atguigu.java;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 其它常用类的使用：
 * 1. System
 * 2. Math
 * 3. BigInteger和BigDecimal
 *
 * @create 2020-05-31 9:30
 **/
public class OtherClassTest
{
    public static void main(String[] args)
    {
        test1();
    }

    public static void test1()
    {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));

        BigInteger bi = new BigInteger("123123123132123");
        BigDecimal bd = new BigDecimal("12313156.465");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);

        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));

    }

}
