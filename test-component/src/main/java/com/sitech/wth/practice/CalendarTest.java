package com.sitech.wth.practice;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author: wangth_oup
 * @date: 2021-10-22 16:18
 * @description: 日历
 **/
public class CalendarTest {

    //  请输入您想要查看的日期：（提示：请按照例如2021-10-22的格式书写）2021-10-22
    //  日	一	二	三	四	五	六
    //  					1	2
    //  3	4	5	6	7	8	9
    //  10	11	12	13	14	15	16
    //  17	18	19	20	21	22*	23
    //  24	25	26	27	28	29	30
    //  31
    public static void main(String[] args) {
        //1.录入日期的字符串
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入您想要查看的日期：（提示：请按照例如2021-10-22的格式书写）");
        String strDate = sc.next();
        //2.String ---> Calender
        java.sql.Date date = java.sql.Date.valueOf(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //3.星期提示
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        //4.获取本月的最大天数
        int maxDay = cal.getActualMaximum(Calendar.DATE);
        int nowDay = cal.get(Calendar.DATE);
        //将日期调整为本月的1号
        cal.set(Calendar.DATE, 1);
        //获取1号是本周的第几天
        int num = cal.get(Calendar.DAY_OF_WEEK);
        //前面空出来的天数 num-1

        //遍历空格
        int count = 0;//计数器
        for (int i = 1; i <= num - 1; i++) {
            System.out.print("\t");
            count++;
        }
        //5.遍历，从1号到maxDay
        for (int i = 1; i <= maxDay; i++) {
            if(i==nowDay){
                System.out.print(i+"*"+"\t");
            } else {
                System.out.print(i+"\t");
            }
            count++;
            if(count%7 == 0){
                System.out.println();
            }
        }
    }

}
