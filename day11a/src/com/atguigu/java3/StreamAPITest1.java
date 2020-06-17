package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
    测试Stream的中间操作
 */
public class StreamAPITest1
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test1()
    {
//        filter(Predicate p) - 接收Lambda ，从流中排除某些元素。
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();

        //练习:查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();

        //        limit(n) - 截断流，使其元素不超过给定数量。
        employees.stream().limit(3).forEach(System.out::println);

        System.out.println();

//        skip(n)跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n) 互补
        employees.stream().skip(3).forEach(System.out::println);

        System.out.println();

//        distinct() -筛选，通过流所生成元素的hashCode(和equals()去除重复元素
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        System.out.println(employees);

        employees.stream().distinct().forEach(System.out::println);

    }

    //映射
    public static void test2()
    {
//        map(Function f)---接收一个函数作为参数 ，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> strings = Arrays.asList("aa", "bb", "cc", "dd");
        strings.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

//        练习1:获取员工姓名长度大于3的员工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        //方式一：
//        employees.stream().map(str -> str.getName());
        //方式二：
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name -> name.length() > 3).forEach(System.out::println);

        System.out.println();

        //练习2：
        Stream<Stream<Character>> streamStream = strings.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s -> s.forEach(System.out::println));

        System.out.println();

//        flatMap(Function f)_接收一 个函数作为参数 ，将流中的每个值都换成另一个流 ，然后把所有流连接成一个流。
        Stream<Character> characterStream = strings.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str) //aa
    {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray())
        {
            list.add(c);
        }
        return list.stream();
    }


    public static void test3()
    {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        ArrayList<Object> objects1 = new ArrayList<>();
        objects1.add(4);
        objects1.add(5);
        objects1.add(6);

//        objects.add(objects1);
        objects.addAll(objects1);
        System.out.println(objects);

    }

    //3-排序
    public static void test4()
    {
        //sorted()-- 自然排序
        List<Integer> integers = Arrays.asList(-1, 0, 1, 9, 5);
        integers.stream().sorted().forEach(System.out::println);
        //涉及自然排序需要实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);

        //sorted(Comparator com) 一定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) ->
        {
            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if (compare != 0)
            {
                return compare;
            } else
            {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }
}
