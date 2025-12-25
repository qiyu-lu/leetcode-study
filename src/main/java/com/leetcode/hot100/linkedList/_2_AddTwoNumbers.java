package com.leetcode.hot100.linkedList;

public class _2_AddTwoNumbers {
    /**
     * 2 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int temp = 0;
        while(l1 != null && l2 != null){
            tail.next = new ListNode((l1.val + l2.val + temp) % 10);
            if(l1.val + l2.val + temp >= 10) temp = 1;
            else temp = 0;
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            tail.next = new ListNode((l1.val + temp) % 10);
            if(l1.val + temp >= 10) temp = 1;
            else temp = 0;
            tail = tail.next;
            l1 = l1.next;
        }
        while(l2 != null){
            tail.next = new ListNode((l2.val + temp) % 10);
            if(l2.val + temp >= 10) temp = 1;
            else temp = 0;
            tail = tail.next;
            l2 = l2.next;
        }
        if(temp == 1) tail.next = new ListNode(1);
        return dummy.next;
    }
}
