package com.leetcode.hot100.linkedList;

import java.util.List;

public class _206_ReverseList {
    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        //需要三个节点，来保证不会丢掉指向
        ListNode prev = null;//当前的节点如果想要反转 需要 指向的节点
        ListNode curr = head;//当前的节点，反转后就是链表的头节点

        while(curr != null){
            //当前节点的下一个节点，如果没有记录这个节点，当前节点和之前的节点翻转后，这个节点就丢了
            ListNode next = curr.next;
            curr.next = prev;//断开原来的指向，指向已经翻转的那个链表
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
