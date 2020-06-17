package com.atguigu.java2;


import com.atguigu.java1.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
    获取运行时类的方法结构
 */
public class MethodTest
{
    public static void main(String[] args)
    {
//        test1();
        test2();
    }

    private static void test1()
    {
        Class<Person> personClass = Person.class;
        //getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = personClass.getMethods();
        for (Method m : methods)
        {
            System.out.println(m);
        }

        System.out.println();

        //getDeclaredMethods():获取当前运行时类中声明的所有方法。 (不包含父类中声明的方法)
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method m : declaredMethods)
        {
            System.out.println(m);
        }

    }

    /*
        @Xxx
        权限修饰符返回值类型 方法名(参数类型1 形参名1,...) throws XxxException{}
    */
    public static void test2()
    {
        Class<Person> personClass = Person.class;
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method m : declaredMethods)
        {
            //1. 获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations)
            {
                System.out.println(a);
            }

            //2. 权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");
            //3. 返回类型
            System.out.print(m.getReturnType().getName() + "\t");
            //4. 方法名
            System.out.print(m.getName() + "\t");
            //5. 参数类型
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0))
            {
                for (Class a : parameterTypes)
                {
                    System.out.print(a.getName() + "\t");
                }
            }
            //6. 抛出的异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (!(exceptionTypes == null || exceptionTypes.length == 0))
            {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++)
                {
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }


    }

}
