package com.atguigu.java2;

import com.atguigu.java.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1. List接口框架
 * |----Collection接口:单列集合,用来存储一一个一个的对象
 * |----List接口:存储有序的、可重复的数据。-->“动态”数组,替换原有的数组
 * |----ArrayList：作为List接口的主要实现类，线程不安全的，效率高。底层使用Object存储
 * |----LinkedList：对于频繁的插入、删除操作，使用此类的效率比ArrayList高，底层使用双向链表存储
 * |----Vector：List接口的古老实现类，线程安全的，效率低。底层使用Object存储
 * <p>
 * 2. ArrayList的源码分析:
 * 2.1 jdk 7的情况下
 * ArrayList List = new ArrayList();//底层创建了长度是1e的object[ ]数组eLementData
 * List. add(123);//elementData[0] = new Integer(123);
 * list. add(11);//如果此次的添加导致底层e LementData数组容量不够，则扩容。
 * 默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 * 结论:建议开发中使用带参的构造器: ArrayList list = new ArrayList(int capacity)
 * 2.2 jdk8情况下
 * ArrayList list = new ArrayList();//底层object[] elementData初始化为{}. 并没有创建长度
 * list. add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数据123添加到eLemen
 * 后续的添加和扩容操作与jdk 7无异。
 * 2.3小结: jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象
 * 的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 * <p>
 * 3. LinkedList的原码分析：
 * LinkedList list = new LinkedList(); 内部声明了Node类型的first和ast属性，默认值为n
 * List. add(123);//将123封装到Node中，创建了Node对象。
 * 其中，Node定义为:体现了LinkedList的双向链表的说法
 * private static cLass Node<E> {
 * E item;
 * Node<E> next;
 * Node<E> prev;
 * Node(Node<E> prev, E element, Node<E> next) {
 * this. item = eLement;
 * this.next = next;
 * this.prev = prev;
 * }
 * <p>
 * 4. Vector 的源码分析: jdk7和jdk8 中通过Vector()构造器创建对象时，底层都创建了长度为10的数乡
 * 在扩容方面，默认力容为原来的数组长度的2倍。
 * <p>
 * <p>
 * 5. List接口中的常用方法：
 * void add(int index, object ele): 在index位置插入ele元素
 * boolean addAlL(int index, Collection eles): 从index位置开始将eles中的所有元素添加进来
 * 0bject get(int index): 获取指定index位置的元素
 * int indexOf(Object obj): 返回obj在集合中首次出现的位置
 * int LastIndex0f(Object obj): 返回obj在当前集合中末次出现的位置
 * Object remove(int index): 移除指定index位置的元素，并返回此元素
 * Object set(int index， object ele): 设置指定index位置的元素为ele
 * List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合
 * <p>
 * <p>
 * 总结:常用方法
 * 增: add(0bject obj)
 * 删: remove(int index) / remove(0bject obj)
 * 改: set(int index, Object ele)
 * 查: get(int index)
 * 插: add(int index, object eLe)
 * 长度: size()
 * 遍历:①Iterator迭代器方式
 * ②增强for循环
 * ③普通的循环
 * <p>
 * <p>
 * 面试题：ArrayList、LinkedList、Vector三者的异同？
 * 同：三个类都实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 * 异：见上
 *
 * @create 2020-05-31 23:24
 **/
public class CollectionTest
{

    public static void main(String[] args)
    {
//        test1();
//        test2();
        test3();
    }

    public static void test1()
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add("java");
        arrayList.add(false);
        arrayList.add(123);

        arrayList.add(1, 456);
        System.out.println(arrayList);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        arrayList.addAll(0, integers);
        System.out.println(arrayList.size());
        //添加一个集合
        //[1, 2, 3, 4, 123, 456, java, false, 123, Person{name='TOM', age=12},
        // [1, 2, 3, 4]]
        arrayList.add(integers);
        System.out.println(arrayList.size());
        System.out.println(arrayList);

        System.out.println(arrayList.get(0));

    }

    public static void test2()
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add("java");
        arrayList.add(false);
        arrayList.add(123);

        System.out.println(arrayList.indexOf("java"));
        System.out.println(arrayList.indexOf(123));
        System.out.println(arrayList.lastIndexOf(123));

        Object remove = arrayList.remove(0);
        //根据所以删除，返回删除的元素
        System.out.println(remove);
        System.out.println(arrayList);

        Object set = arrayList.set(0, 123);
        System.out.println("被修改的元素为：" + set);
        System.out.println(arrayList);

        System.out.println(arrayList.subList(1, 3));
    }

    public static void test3()
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add("java");
        arrayList.add(false);

        //方式一：迭代器
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        //方式二：增强for循环
        for (Object o : arrayList)
        {
            System.out.println(o);
        }

        //方式三：普通循环
        for (int i = 0; i < arrayList.size(); i++)
        {
            System.out.println(arrayList.get(i));
        }

    }

}
