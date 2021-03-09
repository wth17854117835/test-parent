package com.sitech.wth.practice.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * stream流是jdk1.8之后出现，关注的是做什么，而不是怎么做
 **/
public class Demo02Stream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        list.stream()
                .filter(name -> name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));
    }

}
