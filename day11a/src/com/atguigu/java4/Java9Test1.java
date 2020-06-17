package com.atguigu.java4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class Java9Test1
{
    public static void main(String[] args)
    {
//        test4();
        new Test().test5();
    }

    //java8中的写法:
    public static void test1()
    {
        List<String> namesList = new ArrayList<>();
        namesList.add("Joe");
        namesList.add("Bob");
        namesList.add("Bi11");
        //设计不可编辑集合namesList
        namesList = Collections.unmodifiableList(namesList);
        System.out.println(namesList);

    }

    public static void test2()
    {
        List<String> list = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        Set<String> set = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "C")));
        //如下操作不适用于idk 8及之前版本,适用于jdk 9
        Map<String, Integer> map = Collections.unmodifiableMap(new HashMap<>()
        {
            {
                put("a", 1);
                put("b", 2);
                put("c", 3);
            }
        });
        map.forEach((k, v) -> System.out.println(k + ":" + v));

    }

    public void test3()
    {
        // 此时得到的集合list也是一个只读集合。
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.add(6);
    }

    //java9新特性:集合工厂方法:创建只读集合
    public static void test4()
    {
        List<Integer> integers = List.of(1, 2, 3, 4);
        System.out.println(integers);

        Set<Integer> integers1 = Set.of(12, 1, 3, 2, 31, 15);
        System.out.println(integers1);

        Map<String, Integer> tom = Map.of("Tom", 23, "Jerry", 54);
        System.out.println(tom);

        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("Tom", 34), Map.entry("Jerry", 21));

    }


}


class Test
{
    //java9新特性九: InputStream 的新方法: tranferTo()
    public void test5()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        try (InputStream is = cl.getResourceAsStream("hello.txt");
             OutputStream os = new FileOutputStream("src/hello1.txt"))
        {
            is.transferTo(os); //把输入流中的所有数据直接自动地复制到输出流中
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}