package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    测试Stream的终止操作
*/
public class StreamAPITest2
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    //1- 匹配与查找
    public static void test1()
    {
        List<Employee> employees = EmployeeData.getEmployees();

//        allMatch(Predicate_ ,p)= 检查是否匹配所有元素。
//        练习:是否所有的员工的年龄都大于18。1
        boolean b = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(b);

//        anyMatch(Predicate_ p)=检查是否至少匹配- -个元素。
//        练习:是否存在员工的工资大于，10000
        boolean b1 = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(b1);

//        noneMatch(Predicate .p)检查是否没有匹配的元素。
//        练习:是否存在员工姓“雷”
        boolean 雷 = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(雷);

//        findFirst-返回第-个元素。
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

//        findAny一返回当前流中的任意元素。
        Optional<Employee> any = employees.stream().findAny();
        Optional<Employee> any1 = employees.parallelStream().findAny();
        System.out.println(any);
        System.out.println(any1);

    }

    public static void test2()
    {
        List<Employee> employees = EmployeeData.getEmployees();
        // count一返回流中元素的总个数。
        long count = employees.stream().count();
        System.out.println(count);

        long count1 = employees.stream().filter(e -> e.getAge() > 30).count();
        System.out.println(count1);

        // max(Comparator c)=返回流中最大值。
        // 练习:返回最高的工资:。
        Stream<Double> doubleStream = employees.stream().map(employee -> employee.getSalary());
        Optional<Double> max = doubleStream.max(Double::compareTo);
        System.out.println(max);

        // min(Comparator. .c)=返回流中最小值
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);

        // 练习:返回最低工资的员工。
        // forEach(Consuner. .c)= 内部迭代。
        employees.stream().forEach(System.out::println);

    }

    //2-归约
    public static void test3()
    {
//        reduce(T identity, BinaryoOperator)- 可以将流中元素反复结合起来，得到一个值。返回T
//        练习1:计算1-10的自然数的和
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

//        reduce(BinaryOperator)一可以将流中元素反复结合起来， 得到一个值。返回Optional<7
//        练习2:计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> reduce1 = employees.stream().map(t -> t.getSalary()).reduce((e1, e2) -> e1 + e2);
        System.out.println(reduce1);

        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Optional<Double> reduce2 = doubleStream.reduce(Double::sum);
        System.out.println(reduce2);

    }


    //3-收集
    public static void test4()
    {
        //练习1：查找工资大于6000的员工，结果返回一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
