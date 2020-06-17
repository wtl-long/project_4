package com.atguigu.java1;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest1
{
    public static void main(String[] args)
    {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            URL url = new URL("https://home.firefoxchina.cn/");
            urlConnection = (HttpURLConnection) url.openConnection();

            //获取链接
            urlConnection.connect();

            //获取流
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("d://a.txt");
            byte[] bytes = new byte[1024];
            int lens;
            while ((lens = inputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes, 0, lens);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                inputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }


    }
}
