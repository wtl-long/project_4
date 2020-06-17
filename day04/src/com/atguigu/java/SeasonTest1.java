package com.atguigu.java;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承java.lang.Enum类
 *
 * @create 2020-05-31 10:25
 **/
public class SeasonTest1
{
    public static void main(String[] args)
    {
        Season1 summer = Season1.SUMMER;
        //toString方法
        System.out.println(summer);

        //查看父类
//        System.out.println(Season1.class.getSuperclass());

//        values方法
        Season1[] values = Season1.values();
        for (Season1 value : values)
        {
            System.out.println(value);
        }

        Thread.State[] values1 = Thread.State.values();
        for (Thread.State s : values1)
        {
            System.out.println(s);
        }

        //valueOf(String objName)
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
        winter.show();
    }


}

//自定义枚举类
enum Season1 implements info
{
    //1. 提供当前枚举类的对象，多个对象之间用“，”隔开
    SPRING("春天", "春暖花开")
            {
                @Override
                public void show()
                {
                    System.out.println("春季");
                }
            },
    WINTER("冬天", "凛冬之怒")
            {
                @Override
                public void show()
                {
                    System.out.println("冬季");
                }
            },
    SUMMER("夏天", "夏日炎炎")
            {
                @Override
                public void show()
                {
                    System.out.println("夏季");
                }
            },
    AUTOMN("秋天", "秋高气爽")
            {
                @Override
                public void show()
                {
                    System.out.println("秋季");
                }
            };


    //1. 声明Season1类的属性:使用private final修饰
    private final String Season1Name;
    private final String Season1Desc;


    //2. 私有化构造器，并给对象属性赋值
    //3. 提供当前枚举类的多个对象：public static final

    private Season1(String Season1Name, String Season1Desc)
    {
        this.Season1Name = Season1Name;
        this.Season1Desc = Season1Desc;
    }

    //4. 其它诉求：获取枚举类对象的属性

    public String getSeason1Name()
    {
        return Season1Name;
    }

    public String getSeason1Desc()
    {
        return Season1Desc;
    }

//    @Override
//    public void show()
//    {
//        System.out.println("这是一个季节");
//
//    }
//    @Override
//    public String toString()
//    {
//        return "Season1{" +
//                "Season1Name='" + Season1Name + '\'' +
//                ", Season1Desc='" + Season1Desc + '\'' +
//                '}';
//    }


}


interface info
{
    void show();
}