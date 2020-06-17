package com.atguigu.java3;

/*
    File类的使用
    1. File类的一个对象，代表一个文件或一个文件目录(俗称:文件夹)
    2. File类声明在java. io包下

 */

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    /*
        1.如何创建File类的实例:
        File(String filePath)
        File(String parentPath, String childPath)
        File(File parentFile, String childPath )

        2.
        相对路径:相较于某个路径下，指明的路径。
        绝对路径:包含盘符在内的文件或文件目录的路径
        3.路径分隔符
        windows:\\
        unix:/

     */
    private static void test1()
    {
        //构造器1:
        File file = new File("hello.txt");
        File file1 = new File("F:\\IDEA\\IDEA_workspace\\project_1\\day07\\he.txt");

        //构造器2:
        File file3 = new File("D: lworkspace_ idea1", "JavaSenior");
        System.out.println(file3);

        //构造器3:
        File file4 = new File(file3, "hi.txt");
        System.out.println(file4);

        System.out.println(File.separator);
        System.out.println(File.separatorChar);
    }

    /*
        public String getAbsolutePath(): 获取绝对路径
        public String getPath() : 获取路径
        public String getName() : 获取名称
        public String getParent(): 获取上层文件目录路径。若无，返回null
        public long Length() :获取文件长度(即:字节数)。不能获取目录的长度。
        public Long LastModified() :获取最后一次的修改时间，毫秒值
        public String[] list() :获取指定目录下的所有文件或者文件目录的名称数组
        public File[] listFiles() :获取指定目录下的所有文件或者文件目录的File数组

        如下两个方法使用于文件目录：
            public String[] list() :获取指定目录下的所有文件或者文件目录的名称数组
            public File[] ListFiles() :获取指定目录下的所有文件或者文件目录的File数组

    */
    private static void test2()
    {
        File file = new File("d:" + File.separator + "a.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.length());
        System.out.println(file.lastModified());
        System.out.println(new Date(file.lastModified()));

    }


    private static void test3()
    {
        File file = new File("F:\\IDEA\\IDEA_workspace\\project_1");
        String[] list = file.list();
        File[] files = file.listFiles();

        for (String s : list)
        {
            System.out.println(s);
        }

        System.out.println();

        for (File s : files)
        {
            System.out.println(s);
        }

    }

    public static void test4()
    {
        /*
            public boolean renameTo(File dest): 把文件重命名为指定的文件路径
            比如: fiLe1. renameTo(fiLe2)为例:剪切+重命名
                要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。

         */
        File file = new File("d:/a.txt");
        File file1 = new File("d:/b.txt");
        System.out.println(file);
        boolean b = file.renameTo(file1);
        System.out.println(b);

    }

    /*
        public boolean isDirectory(): 判断是否是文件目录
        public boolean isFile() :判断是否是文件
        public boolean exists() :判断是否存在
        public boolean canRead() :判断是否可读
        public boolean canWrite() :判断是否可写
        public boolean isHidden() :判断是否隐藏
    */

    public static void test5()
    {
        File file1 = new File("d:/b.txt");
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());

    }

    /*
    创建硬盘中对应的文件或文件目录：
        public boolean createNewFile():创建文件。若文件存在，则不创建，返回false
        public boolean mkdir() :创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
        public boolean mkdirs():创建文件目录。如果上层文件目录不存在，一并创建

    删除磁盘中的文件或文件目录：
        public boolean delete():删除文件或者文件夹
        删除注意事项:
            Java中的删除不走回收站。

     */
    public static void test6()
    {
        //文件创建
        File file = new File("d:/a.txt");
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
                System.out.println("创建成功!");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            file.delete();
            System.out.println("删除成功!");
        }

        //文件目录的创建


    }

}
