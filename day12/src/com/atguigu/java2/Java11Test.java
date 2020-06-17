package com.atguigu.java2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @create 2020-06-07 22:41
 **/
public class Java11Test
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
        test3();
    }

    //java 11新特性一: String 中新增的方法
    public static void test1()
    {
        //isBlank():判断字符串是否为空白
        System.out.println(" \t \n ".isBlank());
        //strip() :去除首尾空白
        System.out.println("----" + "   \t   \n ".strip() + "-----");
        System.out.println("----" + "  abv \t   \n ".trim() + "-----");
        //stripTrailing():去除尾部空格
        System.out.println("----" + "  avc \t   \n ".stripTrailing() + "-----");
        //stripLeading():去除首部空格
        System.out.println("----" + "  avc \t   \n ".stripLeading() + "-----");
        //repeat( int count):复制字符串
        var str = "avv";
        String repeat = str.repeat(5);
        System.out.println(repeat);
        //lines().count():行数统计

        var a = "anfa\nfhf\nsa\nnk";
        System.out.println(a.lines().count());

    }

    //java11新特性: Optional新增的方法
    public static void test2()
    {
        var op = Optional.empty();
        System.out.println(op.isPresent());//判断内部的value是否存在
        System.out.println(op.isEmpty());//判断内部的value是否为空

        //orELse Throw( ):vaLue非空，返回value; 否则抛异常NoSuchELementException
        op = Optional.of("abc");
        var o = op.orElseThrow();
        System.out.println(o);

        //or:value非空，返回对应的Optional; value为空， 返回形参封装的Optional
        Optional<String> op1 = Optional.of("hello");
        Optional<Object> op2 = op.or(() -> op1);
        System.out.println(op2);

    }

    //java11新特性三:局部变量类型推断的升级
    public static void test3()
    {
        //错误的形式:必须要有类型，可以加上var
//        Consumer<String> con1 = (@Deprecated t) ->
//        System.out.println(t.toJpperCase());
        //正确的形式:
        //使用var的好处是在使用lambda表达式时给参数加上注解。
        Consumer<String> con2 = (@Deprecated var t) ->
                System.out.println(t.toUpperCase());
    }

    //java11 新特性四: HttpClient 替换原有的HttpURLConnection.
    public static void test4()
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
            HttpResponse<String> response = client.send(request, responseBodyHandler);
            String body = response.body();
            System.out.println(body);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public static void test5()
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1: 8080/test/")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        CompletableFuture<HttpResponse<String>> sendAsync =
                client.sendAsync(request, responseBodyHandler);
        sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
        //HttpResponse<String> response = sendAsync.get();
        //String body = response . body();
        //System. out。println(body);

    }
}