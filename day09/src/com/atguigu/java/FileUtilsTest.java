package com.atguigu.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @create 2020-06-04 16:04
 **/
public class FileUtilsTest
{
    public static void main(String[] args)
    {
        File file = new File("d:/a.txt");
        File file1 = new File("d:/c.txt");
        try
        {
            FileUtils.copyFile(file, file1);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
