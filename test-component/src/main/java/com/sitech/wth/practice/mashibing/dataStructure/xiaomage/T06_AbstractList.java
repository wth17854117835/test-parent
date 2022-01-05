package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

/**
 * @author: wangth_oup
 * @date: 2021-08-26 11:26
 * @description: 抽象类不对外公开，只负责抽取一些公共的方法
 **/
public abstract class T06_AbstractList<E> implements T05_List<E> {
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size=" + size);
    }

    protected void rangeCheck(int index) {
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if(index < 0 || index > size){
            outOfBounds(index);
        }
    }
}
