package com.sitech.wth.practice.mashibing.dataStructure.xiaomage;

/**
 * @author: wangth_oup
 * @date: 2021-08-26 10:15
 * @description:
 **/
public class T04_LinkedList<E> extends T06_AbstractList<E> {

    private Node<E> first;

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).elmentData;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElmentData = node.elmentData;
        node.elmentData = element;
        return oldElmentData;
    }

    @Override
    public void add(int index, E element) {
        if (index == 0) {
            first = new Node<>(element, first);
        } else {
            Node<E> preNode = node(index - 1);
            Node<E> newNode = new Node<>(element, preNode.next);
            preNode.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> preNode = node(index - 1);
            node = preNode.next;
            preNode.next = preNode.next.next;
            size--;
        }
        return node.elmentData;
    }

    @Override
    public int indexOf(E element) {
        if(element == null){
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if(node.elmentData == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if(element.equals(node.elmentData)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        E elmentData;
        Node<E> next;

        public Node(E elmentData, Node<E> next) {
            this.elmentData = elmentData;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(", [");
        Node<E> node = first;
        while (node != null) {
            if(node == first){
                sb.append(node.elmentData);
            } else {
                sb.append("," + node.elmentData);
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
