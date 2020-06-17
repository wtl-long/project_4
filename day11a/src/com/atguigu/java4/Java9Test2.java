package com.atguigu.java4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @create 2020-06-07 18:39
 **/
public class Java9Test2
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    //java9新特性十: Stream API的加强
    public static void test1()
    {
        List<Integer> integers = Arrays.asList(1, 456, 12, 465, 32, 46, 6);
        //takeWhile返回从开头开始的按照指定规则尽量多的元素
        integers.stream().takeWhile(x -> x < 60).forEach(System.out::println);

        System.out.println();

        //dropWhile():与takeWhile相反，返回剩余的元素。
        integers.stream().dropWhile(x -> x < 60).forEach(System.out::println);
    }

    public static void test2()
    {
        //of()参数中的多个元素，可以包含nulL值
        Stream<Integer> stream1 = Stream.of(1, 2, 3, null);
        stream1.forEach(System.out::println);


        //错误：of()参数不能存储单个nuLl值
//        Stream<Object> objectStream = Stream.of(null);
        //正确
//        Stream<Object> objectStream = Stream.of(null, null);

        Integer i = 10;
        i = null;
        Stream<Integer> integerStream = Stream.ofNullable(i);
        System.out.println(integerStream.count());

    }

    public static void test3()
    {
//        Stream.iterate(0, x -> x + 1).limit(100).forEach(System.out::println);

        //java9中新增的重载的方法
        Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);

    }

    //java9新特性十- : Optional提供J新的方法stream()
    public static void test4()
    {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");
        Optional<List<String>> optional = Optional.ofNullable(list);
//        Stream<List<String>> stream = optional.stream();
//        stream.forEach(System.out::println);

        System.out.println();

//        long count = stream.count();
//        System.out.println(count);
//        stream.flatMap(s -> s.stream()).forEach(System.out::println);


    }

}
