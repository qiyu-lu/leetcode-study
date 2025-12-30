package com.leetcode.hot100.linkedList;


public class _148_SortList {
    /**
     * 148 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p1 = head;
        ListNode p2 = p1;
        ListNode temp = null;
        while(p2 != null && p2.next != null){
            p2 = p2.next.next;//快慢指针这里经常错
            temp = p1;
            p1 = p1.next;
        }//找到中点 p1 就是中点对应的节点
        //将左右两个链表断开
        temp.next = null;

        ListNode leftHead = sortList(head);//左半段
        ListNode rightHead = sortList(p1);//右半段
        //上面的操作使链表变为两个有序的链表
        return mergeTwoLists(leftHead, rightHead);
    }
    private ListNode mergeTwoLists(ListNode head1, ListNode head2){
        if(head1 == null) return head2;
        if(head2 == null) return head1;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while(head1 != null && head2 != null){
            if(head1.val < head2.val){
                pre.next = head1;
                head1 = head1.next;
                pre = pre.next;
            }
            else{
                pre.next = head2;
                head2 = head2.next;
                pre = pre.next;
            }
        }
        if(head1 != null) pre.next = head1;
        if(head2 != null) pre.next = head2;
        return dummy.next;
    }

}
