package com.atguigu.java;

import java.util.Calendar;
import java.util.Date;

/**
 * @create 2020-05-30 10:37
 **/
public class DateTimeTest 
{
    /*
        java.util.Date类
                子类：java.sql.Date类

        1. 两个构造器的使用
            //构造器一：Date():创建一个对应当前时间的Date对象
            //构造器二：创建指定毫秒数的Date对象

        2. 两个方法的使用
            >toString()显示：Sat May 30 10:43:43 CST 2020
            >getTime()显示：1590806733767

        3. java.sql.Date：对应数据库中的日期类型的变量
        >如何实例化
        >如何util.Date--->sql.Date对象
     */

    public static void test2()
    {
        //构造器一：Date():创建一个对应当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1.toString());
        System.out.println(date1.getTime());

        //构造器二：创建指定毫秒数的Date对象
        Date date2 = new Date(1590806733767L);
        System.out.println(date2);

        //创建java.sql.Date对象
        java.sql.Date date3 = new java.sql.Date(21345616513265L);
        System.out.println(date3);  //2646-06-02

        //如何util.Date--->sql.Date对象
        //情况一:强制转化
//        java.util.Date date4 = new java.sql.Date(21345616513265L);
//        java.sql.Date date5=(java.sql.Date)date4;
        //情况二
        Date date6=new Date();
        java.sql.Date date7=new java.sql.Date(date6.getTime());


    }


    public static void main(String[] args) {
//        test1();
//        test2();
        testCalendar();
    }

    public static void test1()
    {
        //1. 返回1970年1月1日0时0分0秒至今以毫秒为单位的时间差，称为时间戳
        long l = System.currentTimeMillis();
        System.out.println(l);
    }

    //Calendar日历类(抽象类)的使用
    public static void testCalendar()
    {
        //1. 实例化
        //方式一：创建其子类(GregorianCalendar)的对象
        //方式二：调用静态方法getInstance()
        Calendar c = Calendar.getInstance();
//        System.out.println(c.getClass());
        //2. 常用方法
        //get
        int days = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

        //set
        c.set(Calendar.DAY_OF_YEAR,1);
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

        //add
        c.add(Calendar.DAY_OF_YEAR,3);
        //减
        c.add(Calendar.DAY_OF_YEAR,-3);
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

        //getTime  Calendar--->Date
        Date time = c.getTime();

        //setTime  Date--->Calendar
        Date time2=new Date();
        c.setTime(time2);
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

    }

}
