package com.sitech.wth.practice.mashibing.dataStructure.leetCode.链表;

/**
 * @author: wangth_oup
 * @date: 2021-08-30 20:54
 * @description: 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 **/
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
