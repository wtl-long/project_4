package com.atguigu.java;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @create 2020-06-02 14:54
 **/
public class PropertiesTest
{
    //Properties:常用来处理配置文件。key和value 都是String类型

    public static void main(String[] args) throws Exception
    {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
        properties.load(fileInputStream);       //加载流对应的文件
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println(name + "\n" + password);
    }


}
