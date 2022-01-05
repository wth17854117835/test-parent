package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

import com.sitech.wth.practice.mashibing.dataStructure.xiaomage.util.AssertUtils;
import io.swagger.models.auth.In;

/**
 * @author: wangth_oup
 * @date: 2021-08-23 15:08
 * @description: 设计一个动态数组，类似于ArrayList
 **/
public class T02_DynamicArray {

    public static void main(String[] args) {
//        int[] array = new int[] {11,22,33};
//        T03_ArrayList<Integer> arrayList = new T03_ArrayList<>();
//        arrayList.add(99);
//        arrayList.add(88);
//        arrayList.add(77);
//        arrayList.set(2, 11);
//        AssertUtils.test(arrayList.get(2) == 11);
//        System.out.println(arrayList);

        T05_List<Integer> linkedList = new T04_LinkedList<>();
        linkedList.add(20);
        linkedList.add(0,10);
        linkedList.add(30);
        linkedList.add(linkedList.size(),40);

        linkedList.remove(1);

        System.out.println(linkedList);
    }

}
