package com.sitech.wth.aop;

import java.lang.annotation.*;

/**
 * @author: wangth_oup
 * @date: 2020-10-19 19:29
 * @description: 自定义注解用于打印Controller层方式日志
 *
 * Retention: 用来修饰注解，是注解的注解，称为元注解。
 * Target:用来说明对象的作用范围
 * Documented:用来做标记使用
 * RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃（运行时去动态获取注解信息）
 * RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期（在编译时进行一些预处理操作）
 * RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在（做一些检查性的操作）
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ControllerMethodLog {
}
