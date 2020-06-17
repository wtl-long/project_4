package com.atguigu.java;

/**
 * @create 2020-05-30 13:13
 **/
public class InstanceOf
{
    public static void main(String[] args)
    {
        People people=new Student();
        System.out.println(people.getClass());
        System.out.println(people instanceof People);
        System.out.println(people instanceof Student);
        System.out.println(people instanceof Object);
        System.out.println(people instanceof InstanceOf);
    }
}


interface People
{

}

class Student implements People
{

}