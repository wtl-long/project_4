package com.atguigu.nio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe
{
    public static void main(String[] args) throws Exception
    {
        test1();
    }

    public static void test1() throws IOException
    {
        //1. 获取管道
        Pipe open = Pipe.open();

        //2. 将缓冲区的数据写入到管道

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sink = open.sink();

        allocate.put("通过单向管道发送数据".getBytes());
        allocate.flip();
        sink.write(allocate);

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel source = open.source();
        allocate.flip();
        int read = source.read(allocate);
        System.out.println(new String(allocate.array(), 0, read));

        source.close();
        sink.close();


    }

}
