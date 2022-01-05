package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

/**
 * @author: wangth_oup
 * @date: 2021-08-23 15:18
 * @description: 自定义动态数组
 **/
public class T03_ArrayList<E> extends T06_AbstractList<E> {

//    private int size; //元素的数量
    private Object[] elements; //所有的元素
    private static final int DEFAULT_SIZE = 10;
//    private static final int ELEMENT_NOT_FOUND = -1;

    public T03_ArrayList() {
//        elements = new int[DEFAULT_SIZE];
        this(DEFAULT_SIZE);
    }

    public T03_ArrayList(int size) {
        size = size < DEFAULT_SIZE ? DEFAULT_SIZE : size;
        elements = new Object[size];
    }

    /**
     * 清除所有元素
     */
    public void clear(){
//        if(size < 100){
//            size = 0;
//        } else {
//            elements = null;
//        }
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 元素的数量
     */
//    public int size(){
//        return size;
//    }

    /**
     * 是否为空
     */
//    public boolean isEmpty(){
//        return size == 0;
//    }

    /**
     * 是否包含某个元素
     */
//    public boolean contains(E element){
//        return indexOf(element) != ELEMENT_NOT_FOUND;
//    }

    /**
     * 添加元素到尾部
     */
//    public void add(E element){
////        elements[size] = element;
////        size++;
//        //elements[size++] = element;
//        add(size, element);
//    }

    /**
     * 获取index位置的元素
     */
    public E get(int index){
        rangeCheck(index);
        return (E) elements[index];
    }

    /**
     * 设置index位置的元素，返回原来的元素
     */
    public E set(int index, E element){
        rangeCheck(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     */
    public void add(int index, E element){
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);
//        for (int i = size - 1; i >= index; i--) {
//            elements[i+1] = elements[i];
//        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
        //动态扩容
    }

    /**
     * 清除指定索引的元素
     */
    public E remove(int index){
        rangeCheck(index);
        E old = (E) elements[index];

//        for (int i = index + 1; i <= size - 1; i++) {
        for (int i = index + 1; i < size; i++) {
            elements[i-1] = elements[i];
        }
//        size--;
        //最后一个位置清空
//        elements[size] = null;
        //size-- 和 elements[size] = null ===> 合成一句
        elements[--size] = null;
        return old;
    }

    /**
     * 清除指定元素
     */
    public void remove(E element){
        remove(indexOf(element));
    }

    /**
     * 查看元素的索引
     */
    public int indexOf(E element){
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(elements[i] == element) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
//                if(elements[i].equals(element)) {
                if(element.equals(elements[i])) {
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 保证要有capacity的容量
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if(oldCapacity < capacity){
            //相当于x1.5  1+1/2 = 1.5
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = (E) elements[i];
            }
            elements = newElements;
        }
    }

//    private void outOfBounds(int index) {
//        throw new IndexOutOfBoundsException("Index:" + index + ",Size=" + size);
//    }
//
//    private void rangeCheck(int index) {
//        if(index < 0 || index >= size){
//            outOfBounds(index);
//        }
//    }
//
//    private void rangeCheckForAdd(int index) {
//        if(index < 0 || index > size){
//            outOfBounds(index);
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if(i != 0){
                sb.append(", ");
            }
            sb.append(elements[i]);
//            if(i != size-1){
//                sb.append(", ");
//            }
        }
        sb.append("]");
        return sb.toString();
    }
}
