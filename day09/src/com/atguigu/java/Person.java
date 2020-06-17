package com.atguigu.java;

import java.io.Serializable;

/*
    Person需要满足如下的要求，方可序列化
    1.需要实现接口: Serializable
    2.当前类提供一个全局常量: serialVersionUID
    3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性
    也必须是可序列化的。(默认情况下，基本数据类型是可序列化的)

    补充：ObjectOutputStream和IObjectInputStream不能序列化static利和transient修
    饰的成员变量

 */
public class Person implements Serializable
{
    static final long serialVersionUID = 4245615L;
    static String name;
    transient int age;
    private Account account;

    public Person(int age, String name)
    {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Account account)
    {
        this.name = name;
        this.age = age;
        this.account = account;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}


class Account
{
    private double balances;
    private String user;
    private int id;

    public Account(double balances, String user, int id)
    {
        this.balances = balances;
        this.user = user;
        this.id = id;
    }

    public Account()
    {
    }

    public double getBalances()
    {
        return balances;
    }

    public void setBalances(double balances)
    {
        this.balances = balances;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}