package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2020-06-02 16:35
 **/
public class SubOrder extends Order<Integer>    //SubOrder1<T>:不再是泛型类
{
    public static <E> List<E> copyFromArrayToList(E[] arr)
    {
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr)
        {
            list.add(e);
        }
        return list;
    }

}
