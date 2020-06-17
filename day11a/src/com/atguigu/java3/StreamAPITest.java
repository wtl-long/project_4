package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    1. Stream, 关注的是对数据的运算，与CPU打交道
    集合关注的是数据的存储，与内存打交道

    2. @Stream自己不会存储元素。
    ②Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream。
    ③Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行

    3. Stream执行流程:
    ①Stream的实例化
    ②一系列的中间操作(过滤、映射、...)
    ③终止操作

    4. 说明:
    4.1一个中间操作链，对数据源的数据进行处理
    4.2 - 旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用

    测试Stream的实例化


 */
public class StreamAPITest
{
    public static void main(String[] args)
    {
        test4();
    }

    //创建Stream的方式一：通过集合
    public static void test1()
    {
        List<Employee> employees = EmployeeData.getEmployees();

        //default Stream<E> stream() :返回一个顺序流
        Stream<Employee> stream = employees.stream();

        //default Stream<E> parallelStream() :返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();


    }

    //创建Stream的方式二：通过数组
    public static void test2()
    {
        int arr[] = {1, 2, 3, 4};
        //调用Arrays类的,static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee employee1 = new Employee(1001, "Tom");
        Employee employee2 = new Employee(1002, "Jerry");
        Employee employees[] = new Employee[]{employee1, employee2};
        Stream<Employee> stream1 = Arrays.stream(employees);

    }

    //创建Stream方式三:通过stream的of()
    public static void test3()
    {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

    }

    //创建Stream方式四:创建无限流
    public static void test4()
    {
        //迭代
        //public static<T > Stream < T > iterate( final T seed, final Unaryoperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        //public static<T> Stream < T > generate(Supplier < T > s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
