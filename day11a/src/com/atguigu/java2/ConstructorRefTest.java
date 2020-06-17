package com.atguigu.java2;

 /*
  一、构造器引用


  二、数组引用
 */

import java.util.function.Supplier;

public class ConstructorRefTest
{
    public static void main(String[] args)
    {

    }

    //构造器引用
    //Supplier中的T get()
    public static void test1()
    {
        Supplier<Employee> sup = new Supplier<Employee>()
        {
            @Override
            public Employee get()
            {
                return new Employee();
            }
        };

        System.out.println("****************");
        Supplier<Employee>
                sup1 = () -> new Employee();

        System.out.println("**********************");

        Supplier<Employee> sup2=Employee::new;

    }

    //Function中的R apply(T t)
    public static void test2()
    {

    }

    //BiFunction中的R apply(T t,U u)
    public static void test3()
    {

    }

    //数组引用
    //Function中的R apply(T t)
    public static void test4()
    {

    }
}
