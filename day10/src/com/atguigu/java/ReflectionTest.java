package com.atguigu.java;

/*

 */

import java.lang.annotation.ElementType;
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

    //反射之前，对Person操作
    public static void test1()
    {
        Person person = new Person("张三", 20);
        System.out.println(person);
        person.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如: 私有属性 、私有方法、私有的构造器

    }

    //反射之后，对Person的操作
    public static void test2() throws Exception
    {
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("李四", 12);
        //不用强制转化输出的也是Person的toString
        System.out.println(obj);
        Person person = (Person) obj;
        System.out.println(person);

        Method show = personClass.getDeclaredMethod("show");
        show.invoke(person);

        Field f = personClass.getDeclaredField("age");
        f.set(person, 20);
        System.out.println(person);

        //通过反射，可以调用Person类的私有结构的。比如:私有的构造器、方法、属性
        //调用私有的构造器
        Constructor declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object jerry = declaredConstructor.newInstance("jerry");
        System.out.println(jerry);

        //调用私有的属性
        Field declaredField = personClass.getDeclaredField("name");
        declaredField.setAccessible(true);
        declaredField.set(jerry, "Tom");
        System.out.println(jerry);

        //调用私有的方法
        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        Object data = showNation.invoke(jerry, "中国");//相当于jerry.showNation("中国");
        System.out.println(data);
    }

    //疑问1: 通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个?
    //建议直接new的方式
    //什么还是会用到反射。反射的特征：动态性
    //疑问2: 反射机制与面向对象中的封装性是不是矛盾的?如何看待两个技术?
    //不矛盾。

    /*
        关于java. Lang. Class类的理解
        1.类的加载过程:
        程序经过javac.exe命令以后，会生成一一个或多个字节码文件(.class结尾)。
        接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。
        此过程就称为类的加载。加载到内存中的类我们称为运行时类，此运行时类，就作为Class的一个实例

        2. 换句话说，Class的实例就对应着一个运行时类。

        3. 加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
        来获取此运行时类。

     */


    //获取Class实例的方式(前三种方式需要掌握)
    public static void test3()
    {
        //方式一：调用运行时类的属性：.class
        Class<Person> personClass = Person.class;

        //方式二：通过运行时类的对象，调用getClass方法
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        //方式三：调用Class的静态方法：forName(String fullPath);
        Class<?> aClass1 = null;
        try
        {
            aClass1 = Class.forName("com.atguigu.java.Person");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println(personClass);
        System.out.println(aClass);
        System.out.println(aClass1);

        //方式四：使用类的加载器
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> aClass2 = null;
        try
        {
            aClass2 = classLoader.loadClass("com.atguigu.java.Person");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println(aClass2);
        System.out.println(personClass == aClass);
        System.out.println(personClass == aClass2);

    }

    //Class实例可以是那些结构的说明：
    public static void test4()
    {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;
        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要数组的元素类型与维度一样，就是同一个CLass
        System.out.println(c10 == c11);
    }


}
