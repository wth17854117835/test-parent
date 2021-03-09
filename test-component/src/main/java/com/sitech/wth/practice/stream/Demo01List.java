package com.sitech.wth.practice.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 **/
public class Demo01List {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        //过滤，以张开头的，存储到新集合
        List<String> listA = new ArrayList<>();
        for (String s : list) {
            if(s.startsWith("张")){
                listA.add(s);
            }
        }

        //对listA进行过滤，姓名长度为3的，存储到新集合中
        List<String> listB = new ArrayList<>();
        for (String s : listA) {
            if(s.length()==3){
                listB.add(s);
            }
        }

        //遍历listB集合
        for (String s : listB) {
            System.out.println(s);
        }

    }

}
