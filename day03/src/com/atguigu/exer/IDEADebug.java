package com.atguigu.exer;

/**
 * @create 2020-05-30 11:43
 **/
public class IDEADebug
{

    public static void testStringBuff()
    {
        String str=null;
        StringBuffer sb=new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());    //4

        System.out.println(sb); //输出“null”，不是null

        StringBuffer sb1=new StringBuffer(str); //抛异常
        System.out.println(sb1);
    }

    public static void main(String[] args)
    {
        testStringBuff();
    }
}
