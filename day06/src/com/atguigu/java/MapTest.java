package com.atguigu.java;

import java.util.*;

/*
 * 一、Map类的实现类的结构：
 * /----Map:双列数据，存储key-value对的数据 --- 类似于高中的函数: y = f(x)
 *      /----HashMap: 作为Map的主要实现类;线程不安全的，效率高;存储null的key和vaLue
 *           /----LinkedHashMap:保证在遍历map元素时， 可以按照添加的顺序实现遍历。
 *                               原因:在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素，
 *                               适用于频繁的插入、删除操作
 *      /---- TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时只考虑key的自然排序或定制排序，
 *                      底层使用红黑树
 *      /----Hashtable:作为古老的实现类;线程安全的，效率低;不能存储nuLL的key和value
 *           /----Properties:常用来处理配置文件。key和value都是String类型
 *
 * HashMap的底层:数组+链表(jdk7及之前)
 * 数组+链表+红黑树(jdk 8)
 *
 * 面试题:
 * 1. HashMap 的底层实现原理?
 * 2. HashMap 和Hashtable的异同?
 * 3. CurrentHashMap与Hashtable的异同?
 *
 * 二、Map结构的理解:
 * Map中的key:无序的、不可重复的，使用Set 存储所有的key   --->key所在的类要重写equals( )和hashCode( )(以HashMap为例)
 * Map中的vaLue:无序的、可重复的，使用Collection存储所有的value  --->value所在的类要重写equals
 * 一个键值对: key-value构成了一个Entry对象。
 * Map中的entry:无序的、不可重复的，使用Set 存储所有的entry
 *
 * 三、HashMap的底层实现原理？（JDK7）
 *HashMap map = new HashMap():
    在实例化以后，底层创建了长度是16的一维数组Entry[] table。
    ……可能已经执行过多次put……
    map. put (key1, value1):
    首先，调用key1所在类的hashCode() 计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry 数组中的存放位置。
    如果此位置_上的数据为空，此时的key1-value1 添加成功。   ---情况1
    如果此位置上的数据不为空，( 意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据
    的哈希值:
            如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1 -value1添加成功。      ---情况2
            如果key1的哈希值和已经存在的某一个数据的哈希 值相同，继续比较:调用key1 所在类的equals()方法，比较:
                    如果equaLs()返回false:此时key1 -value1添加成功。       ---情况3
                    如果equals()返回true:使用value1替换相同key的value值。

    补充:关于情况2和情况3:此时key1 -value1和原来的数据以链表的方式存储。
    在不断的添加过程中，会涉及到打 容问题，默认的扩容方式:扩容为原来容量的2倍，并将原有的数据复制过来。


     jdk8相较于jdk7在底层实现方面的不同:
    1. new HashMap(): 底层没有创建一个长度为16的数组
    2. jdk 8底层的数组是: Node[], 而非Entry[]
    3.首次调用put()方法时，底层创建长度为16的数组
    4. jdk7底层结构只有:数组+链表。jdk8 中底层结构:数组+链表+红黑树。
    当数组的某一个索引位置 上的元素以链表形式存在的数据个数> 8且当前数组的长度> 64时，
    此时此索引位置上的所有数据改为使用红黑树存储


    五、 Map中定义的方法:
        添加、删除、修改操作:
        Object put(object key, object value): 将指定key-value添加到(或修改)当前map对象中
        void putALl(Map m):将m中的所有key-value对存放到当前map中
        Object remove(object key): 移除指定key的key-value对，并返回value
        void clear():清空当前map中的所有数据
        元素查询的操作:
        Object get(0bject key):获取指定key对应的vaLue
        boolean containsKey(Object key):是否包含指定的key
        boolean containsValue(0bject value): 是否包含指定的vaLue
        int size():返回map中key-value对的个数
        boolean isEmpty(): 判断当前map是否为空
        boolean equals(Object obj): 判断当前map和参数对象obj是否相等
        元视图操作的方法:
        Set keySet(): 返回所有key构成的Set集合
        Collection values(): 返回所有value构成的Collection集合
        Set entrySet(): 返回所有key-value对构成的Set集合



 * @create 2020-06-01 22:13
 */
public class MapTest
{
    public static void main(String[] args)
    {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test1()
    {
        Map map = new HashMap();
        map.put(null, null);

    }

    public static void test2()
    {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);
        map.put("AA", 87);

        System.out.println(map);

        HashMap hashMap = new HashMap();
        hashMap.put("ww", 1456);
        hashMap.put("aa", 1456);

        map.putAll(hashMap);
        System.out.println(map);

        Object ww = map.remove("ww");
        System.out.println(ww);

        System.out.println(map);

        map.clear();
        System.out.println(map.size());

    }

    public static void test3()
    {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);
        map.put("AA", 87);

        System.out.println(map.get("AA"));
        System.out.println(map.containsKey("BB"));
        System.out.println(map.containsValue(12223));

        System.out.println(map.isEmpty());
    }

    public static void test4()
    {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);
        map.put("AA", 87);

        //遍历所有的key集：keySet
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        System.out.println();

        //遍历所以的value集：values
        Collection values = map.values();
        for (Object o : values)
        {
            System.out.println(o);
        }

        System.out.println();

        //遍历key-value
        Set set1 = map.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext())
        {
            //方式一
//            System.out.println(iterator1.next());

//            //方式二
            Object next = iterator1.next();
            Map.Entry e = (Map.Entry) next;
            System.out.println(e.getKey() + "--->" + e.getValue());

        }
        System.out.println();

        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while (iterator2.hasNext())
        {
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + "-->" + value);
        }
    }
}
