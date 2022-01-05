package com.sitech.wth.practice.mashibing.dataStructure.xiaomage.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author: wangth_oup
 * @date: 2021-08-23 11:11
 * @description:
 **/
public class TimeUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void check(String title, Task task){
        if (task == null) return;
        title = (title == null) ? "" : ("【" + title + "】");
        System.out.println(title);
        System.out.println("开始：" + sdf.format(new Date()));
        long start = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束：" + sdf.format(new Date()));
        Double delta = (end - start) / 1000.0;
        System.out.println("耗时：" + delta + "秒");
        System.out.println("--------------------------------------");
    }

}
