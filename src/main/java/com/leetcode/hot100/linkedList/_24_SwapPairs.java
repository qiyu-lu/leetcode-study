package com.leetcode.hot100.linkedList;

import java.util.List;

public class _24_SwapPairs {
    /**
     * 24 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，
     * 并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题
     * （即，只能进行节点交换）。
     * */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        //现在最少有两个节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p0 = dummy;//要交换的两个节点之前的那个节点

        //当当前节点后还剩两个节点时继续循环，否则就可以退出
        while(p0.next != null && p0.next.next != null){
            ListNode p1 = p0.next;//要交换的那两个节点的第一个节点
            ListNode p3 = p1.next.next;//要交换的那两个节点之后的那个节点

            p0.next = p1.next;
            p0.next.next = p1;
            p1.next = p3;
            //如果是偶数个节点的话，p3最终会是空
            //如果是奇数个节点的话，p1.next是空

            p0 = p1;
        }
        return dummy.next;
    }
}
