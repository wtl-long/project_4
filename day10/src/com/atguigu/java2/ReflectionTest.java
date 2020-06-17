package com.atguigu.java2;

/*
    调用运行时类中指定的结构：属性、方法、构造器
 */

import com.atguigu.java1.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest
{
    public static void main(String[] args) throws Exception
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test1() throws Exception
    {
        Class<Person> personClass = Person.class;

        //创建运行时类的对象
        Person person = personClass.newInstance();

        //获取指定的属性:要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = personClass.getField("id");

        /*
            设置当前属性的值
            set():参数1:指明设置哪个对象的属性参数2: 将此属性值设置为多少

         */
        id.set(person, 1001);

        /*
            获取当前属性的值
            get():参数1:获取哪个对象的当前属性值
        */
        Object o = id.get(person);
        System.out.println(o);
    }

    /*
        如何操作运行时类中的指定的属性--需要掌摣
    */

    public static void test2() throws Exception
    {
        Class<Person> personClass = Person.class;

        //创建运行时类的对象
        Person person = personClass.newInstance();

        //1. getDeclaredField(String fieldName): 获取运行时类中指定变量名的属性
        Field name = personClass.getDeclaredField("name");

        //2. 保证当前属性是可访问的
        name.setAccessible(true);

        name.set(person, "TOM");
        System.out.println(name.get(person));

    }


    /*
        如何操作运行时类中的指定的方法--需要掌摣
    */
    public static void test3() throws Exception
    {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        /*
            1. 获取指定的某个方法
            getDeclaredMethod():参数1 :指明获取的方法的名称参数2: 指明获取的方法的形参列表
         */
        Method declaredMethod = personClass.getDeclaredMethod("show", String.class);
        declaredMethod.setAccessible(true);
        /*
            invoke():参数1:方法的调用者参数2: 给方法形参赋值的实参
        */
        Object z = declaredMethod.invoke(person, "中国");
        System.out.println(z);

        System.out.println("***********如何调用静态方法****************");

        //private static void showDesc()
        Method showDesc = personClass.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //写法一：
        showDesc.invoke(Person.class);
        //写法二：
//        showDesc.invoke(null);

    }

    /*
      如何操作运行时类中的指定的构造器
  */
    public static void test4() throws Exception
    {
        Class<Person> personClass = Person.class;

        //private Person(String name)
        /*
            1. 获取指定的构造器
            getDeclaredConstructor(): 参数:指明构造器的参数列表
         */
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);

        //2. 调用此构造器创建运行时类的对象
        Person tom = declaredConstructor.newInstance("TOM");
        System.out.println(tom);


    }

}
