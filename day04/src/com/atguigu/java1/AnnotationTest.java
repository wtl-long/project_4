package com.atguigu.java1;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用
 * 1. 理解Annotation：
 * JDK5.0新增，代替配置文件的功能
 * <p>
 * 2. Annotation使用示例
 * 示例一：使用文档相关的注解
 * 示例二：在编译时进行格式检查（JDK内置的三个基本注解）
 *
 * @Override:限定重写父类方法，该注解只能用于方法
 * @Deprecated:用于表示所修饰的元素(类，方法等) 已过时。通常是因为所修饰的结构危险
 * @SuppressWarnings:抑制编译器警告 2020-05-31 11:02
 * 示例三:跟踪代码依赖性，实现替代配置文件功能
 * <p>
 * 3. 如何自定义注解：参照@SuppressWarnings定义
 * 1) 注解声明为：@interface
 * 2) 内部定义成员，通常使用value表示
 * 3) 可以使用成员的默认值，使用default定义
 * 4) 如果自定义的注解没有成员变量，表名是一个标识作用
 * <p>
 * 如果注解有成员，在使用的时候要指定成员的值
 * 自定义注解必须配上注解的信息处理流程(使用反射)才有意义
 * 自定义注解通常都会指明两个注解：Retention、Target
 * <p>
 * 4. JDK提供的4种元注解
 * 元注解：对现有的注解进行解释说明的注解
 * Retention:指定所修饰的Annotation的生命周期: SOURCE\CLASS (默认行为) \RUNTIME
 * 只有声明为RUNTIME生命周期的注解，才能通过反射获取
 * Target:用于指定被修饰的Annotation能用于修饰哪些程序元素
 * ******出现频率较低******
 * Documented:表示所修饰的注解在被javadoc解析时，保留下来
 * Inherited:被他修饰的Annotation将具有继承性
 * <p>
 * 5. 使用反射获取注解信息
 * <p>
 * 6. JDK8中注解的新特性：可重复注解、类型注解
 * 6.1 可重复注解：
 * 1) 在MyAnnotation上声明@Repeatable,成员值为MyAnnotations.class
 * 2) MyAnnotation中的Target、Retention和MyAnnotations中的相同
 * 6.2 类型注解：
 * ELementType.TTPE_PARAMETER表示该注解能写在类型变量的声明语句中(如:泛型声明)。
 * ELementType.TYPE_USE表示该注解能写在使用类型的任何语句中。
 **/

public class AnnotationTest
{
    public static void main(String[] args)
    {
        People p = new Student();
        p.walk();

        Date date = new Date(2020, 10, 10);
        System.out.println(date);

        @SuppressWarnings("unused")
        int sum = 10;

        testgetAnnotation();
    }

    public static void testgetAnnotation()
    {
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0; i < annotations.length; i++)
        {
            System.out.println(annotations[i]);
        }

    }
}

//完整声明
//@MyAnnotation(value = "hello")
//有默认值
//@MyAnnotation()
//只有一个成员变量


//JDK8之前的写法：
//@MyAnnotations({@MyAnnotation("hello"),@MyAnnotation("hi")})
@MyAnnotation("hello")
@MyAnnotation("hi")
class People
{
    private String name;
    private int age;

    public People()
    {
    }

    public People(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void walk()
    {
        System.out.println("人走路");
    }

    public void eat()
    {
        System.out.println("人吃饭");
    }

}

interface info
{
    void show();
}


class Student extends People implements info
{
    @Override
    public void walk()
    {
        System.out.println("学生走路");
    }

    @Override
    public void eat()
    {
        System.out.println("学生吃饭");
    }

    @Override
    public void show()
    {

    }
}


class Generic<@MyAnnotation T>
{
    public void show()throws @MyAnnotation RuntimeException
    {
        ArrayList<@MyAnnotation String> objects = new ArrayList<>();
        int num = (@MyAnnotation int) 10L;
    }
}