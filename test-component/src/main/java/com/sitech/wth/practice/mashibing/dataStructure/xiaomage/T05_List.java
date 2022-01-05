package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

/**
 * @author: wangth_oup
 * @date: 2021-08-26 11:13
 * @description:
 **/
public interface T05_List<E> {
    public static final int ELEMENT_NOT_FOUND = -1;

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(E element);

    E get(int index);

    E set(int index, E element);

    void add(E element);

    void add(int index, E element);

    E remove(int index);

//    void remove(E element);

    int indexOf(E element);


}
