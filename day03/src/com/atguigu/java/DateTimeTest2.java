package com.atguigu.java;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * @create 2020-05-30 12:51
 **/
public class DateTimeTest2
{
    public static void main(String[] args)
    {
//            test1();
//          test2();
        test3();
    }

    /*
        LocalDate、LocalTime、LocalDateTime的使用
     */
    public static void test1()
    {
        //now：获取当前的时间、日期
        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime now2 = LocalDateTime.now();

        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);

        //of：设置指定的时间
        LocalDateTime of = LocalDateTime.of(2020, 10, 01, 13, 13, 43);
        System.out.println(of);

        //getXxx
        System.out.println(of.getDayOfMonth());

        //withXxx
        //设置时间，有返回值，体现了不可变性
        LocalDateTime localDateTime = of.withDayOfMonth(22);
        System.out.println("===================================");
        System.out.println(of);
        System.out.println(localDateTime);

        //plusXxx
        //增加时间
        //不可变性
        LocalDateTime localDateTime1 = localDateTime.plusDays(5);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);

        //minusXxx
        //减少时间
        LocalDateTime localDateTime2 = localDateTime1.minusDays(5);
        System.out.println(localDateTime2);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime3 = localDateTime2.plusDays(9);
        LocalDateTime localDateTime4 = localDateTime2.plusDays(10);
        System.out.println(localDateTime3);
        System.out.println(localDateTime4);

    }

    /**
     * instant的使用
     */

    public static void test2()
    {
        Instant now = Instant.now();    //获取的是格林尼治的时间，与东八区相差8小时
        System.out.println(now);        //2020-05-30T05:22:32.239Z


        //添加偏移量，相当于加8小时
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);     //2020-05-30T13:22:32.239+08:00

        //获取1970到现在的毫秒数
        long l = now.toEpochMilli();
        System.out.println(l);

        //获取毫秒数对应的时间
        Instant instant = Instant.ofEpochMilli(1590816312557L);
        System.out.println(instant);
    }

    /*
        DateTimeFormatter:格式化解析日期、时间
        类似于SimpleDateFormat
     */

    private static void test3()
    {
        //方式一：iso

        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期--->字符串
        LocalDateTime now = LocalDateTime.now();
        String format = isoLocalDateTime.format(now);
        System.out.println(now);
        System.out.println(format);

        //解析:字符串--->日期
        TemporalAccessor parse = isoLocalDateTime.parse("2020-05-30T13:34:01.649");
        System.out.println(parse);


        //方式二：ofLocalizedDateTime
        //FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT:适用于：LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        //格式化
        String format1 = dateTimeFormatter.format(now);
        System.out.println(format1);        //SHORT--->20-5-30 下午1:42

        String format2 = dateTimeFormatter.format(now);
        System.out.println(format2);        //LONG--->2020年5月30日 下午01时44分18秒

        String format3 = dateTimeFormatter.format(now);
        System.out.println(format3);        //MEDIUM--->2020-5-30 13:45:01

        //解析
        TemporalAccessor parse1 = dateTimeFormatter.parse("2020-5-30 13:45:01");
        System.out.println(parse1);

        //Date中特有的格式FULL
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String format4 = dateTimeFormatter1.format(LocalDate.now());
        System.out.println(format4);        //FULL--->2020年5月30日 星期六



        //重点：自定义的格式  ofPattern（“yyyy-MM-dd hh:mm:ss E”）  HH-->24小时  hh-->12小时
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化
        String format5 = dateTimeFormatter2.format(LocalDateTime.now());
        System.out.println(format5);

        //解析
        TemporalAccessor parse2 = dateTimeFormatter2.parse("2020-05-30 13:55:47");
        System.out.println(parse2);

    }
}
