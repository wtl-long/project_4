package com.atguigu.java1;

/*
    URL网络编程
    1.URL:统一资源定位符，对应着互联网的某一资源地址
    2.格式:
    http://LocaLhost: 8080/examples/beauty. jpg?username= Tom
    协议      主机名     端口号     资源地址            参数列表

    public String getProtocol( )    获取该URL的协议名
    public String getHost( )        获取该URL的主机名
    public String getPort( )        获取该URL的端口号
    public String getPath( )        获取该URL的文件路径
    public String getFile( )        获取该URL的文件名
    public String getQuery( )       获取该URL的查询名



 */

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest
{
    public static void main(String[] args)
    {
        try
        {
            URL url = new URL("https://www.bilibili.com/");
            System.out.println("协议:" + url.getProtocol());
            System.out.println("主机:" + url.getHost());
            System.out.println("端口" + url.getPort());
            System.out.println("路径:" + url.getPath());
            System.out.println("文件名:" + url.getFile());
            System.out.println("查询名:" + url.getQuery());


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

}
