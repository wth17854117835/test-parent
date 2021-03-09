package com.sitech.wth.bean;

import java.io.Serializable;

/**
 * @author: wangth_oup
 * @date: 2020-08-05 17:45
 * @description: 测试实体类
 **/
public class Person implements Serializable {
    private static final long serialVersionUID = 3850969367487748125L;
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
