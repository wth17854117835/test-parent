package com.sitech.wth.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: wangth_oup
 * @date: 2020-08-06 17:33
 * @description: 日期时间API
 **/
public class Java8_01 {

    public static int differentDays(Date date1,Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++) {
                if(i%4==0 && i%100!=0 || i%400==0) {
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        } else {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf1.parse("2020-1-1");
            Date date2 = sdf1.parse("2019-12-25");
            int i = differentDays(date2, date1);
            System.out.println(i);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         * now：静态方法，根据当前时间创建对象
           of：静态方法，根据指定日期/时间创建对象
           plusDays,plusWeeks,plusMonths,plusYears：向当前LocalDate 对象添加几天、几周、几个月、几年
           minusDays,minusWeeks,minusMonths,minusYears：从当前LocalDate 对象减去几天、几周、几个月、几年
           plus,minus：添加或减少一个Duration 或Period
           withDayOfMonth,withDayOfYear,withMonth,withYear：将月份天数、年份天数、月份、年份修改为指定的值并返回新的LocalDate 对象
           getDayOfYear：获得年份天数(1~366)
           getDayOfWeek：获得星期几(返回一个DayOfWeek枚举值)
           getMonth：获得月份, 返回一个Month 枚举值
           getMonthValue：获得月份(1~12)
           getYear：获得年份
           until：获得两个日期之间的Period 对象，或者指定ChronoUnits 的数字
           isBefore,isAfter：比较两个LocalDate
           isLeapYear：判断是否是闰年
         */

        //获取当前系统时间 2020-08-06T17:47:42.953
        LocalDateTime localDateTime1 = LocalDateTime.now();

        //2022-05-18
        LocalDate localDate1 = LocalDate.of(2022, 5, 18);
        //12:12:12
        LocalTime localTime1 = LocalTime.of(12, 12, 12);
        //2022-01-03T12:15:16
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 1, 3, 12, 15,16);

        //2023-03-18T17:47:42.953
        LocalDateTime localDateTime3 = localDateTime1.plusYears(3).minusMonths(5).plusWeeks(2).minusDays(2);

        System.out.println(localDateTime1.getDayOfYear());  // 运行结果：219
        System.out.println(localDateTime1.getDayOfWeek());  // 运行结果：THURSDAY
        System.out.println(localDateTime1.getYear());       // 运行结果：2020
        System.out.println(localDateTime1.getMonthValue()); // 运行结果：8
        System.out.println(localDateTime1.getMonth());      // 运行结果：AUGUST
        System.out.println(localDateTime1.getDayOfMonth()); // 运行结果：6
        System.out.println(localDateTime1.getHour());       // 运行结果：17
        System.out.println(localDateTime1.getMinute());     // 运行结果：53
        System.out.println(localDateTime1.getSecond());     // 运行结果：23

        System.out.println(localDateTime1.withYear(2022).withMonth(2).withDayOfMonth(22)); // 2022-02-22T14:02:14.190
        System.out.println(localDateTime1.withDayOfYear(300));

        System.out.println("======================================================================");
        Instant instant1 = Instant.now();    // 默认获取UTC时区
        System.out.println(instant1); // 2020-08-07T06:04:51.180Z
        // 偏移量运算
        OffsetDateTime offsetDateTime = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 运行结果：2020-08-07T14:04:51.180+08:00
        // 获取时间戳
        System.out.println(instant1.toEpochMilli()); //1596780348337
        System.out.println(System.currentTimeMillis());
        // 以Unix元年为起点，进行偏移量运算
        Instant instant2 = Instant.ofEpochSecond(23);
        System.out.println(instant2);

        System.out.println("======================================================================");
        // Duration:用于计算两个“时间”间隔。
        // Period:用于计算两个“日期”间隔 。

        Instant instant_1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant_2 = Instant.now();
        Duration duration = Duration.between(instant_1, instant_2);
        System.out.println(duration.toMillis()); // 1001

        LocalTime localTime_1 = LocalTime.now(); //14:11:06.783
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime_2 = LocalTime.now();
        System.out.println(Duration.between(localTime_1, localTime_2).toMillis()); //1001

        LocalDate localDate_1 = LocalDate.of(2018,8, 7);
        LocalDate localDate_2 = LocalDate.now();
        Period period = Period.between(localDate_1, localDate_2);
        System.out.println(period.getYears());      // 运行结果：1
        System.out.println(period.getMonths());     // 运行结果：11
        System.out.println(period.getDays());       // 运行结果：30

        System.out.println("=================================解析与格式化=====================================");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ISO_DATE;
        LocalDate localDate = LocalDate.now(); //2020-08-07
        LocalTime now = LocalTime.now(); //14:18:59.793
        LocalDateTime localDateTime = LocalDateTime.now(); //2020-08-07T14:17:41.450
        String strDate1 = localDateTime.format(dateTimeFormatter1); //2020-08-07

        // Date -> String
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate2 = dateTimeFormatter2.format(localDateTime); //2020-08-07 14:21:26
        // String -> Date
        LocalDateTime localDateTime4 = LocalDateTime.parse(strDate2, dateTimeFormatter2); //2020-08-07T14:21:26

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date()); // 2020-08-07 14:27:52
        try {
            Date parse = sdf.parse("2020-08-07 14:27:52");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
