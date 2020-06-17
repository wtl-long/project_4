package com.atguigu.java4;

/**
 * @create 2020-06-07 17:12
 **/
public class MyInterfaceImpl implements MyInterface
{

    @Override
    public void methodAbstract()
    {

    }

    @Override
    public void methodDefault()
    {
        System.out.println("实现类重写");
    }


    public static void main(String[] args)
    {
        //接口中的静态方法只能由接口自己调用
        MyInterface.methodStatic();

        //接口的实现类不能调用接口的静态方法
//        MyInterfaceImpl. methodStatic();

        MyInterfaceImpl myInterface = new MyInterfaceImpl();
        myInterface.methodDefault();

    }
}
