package com.leetcode.hot100.linkedList;

import java.util.ArrayList;
import java.util.List;

public class _234_IsPalindrome {
    /**
     * 234 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * */
    // 我的想法是将链表翻转看一下值是否一致
    public boolean isPalindrome1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode head1 = head;
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        ListNode head2 = ReverseList(head1);
        for(int i = 0; i < list.size() / 2; i++){
            if(list.get(i) != head2.val) return false;
            head2 = head2.next;
        }
        return true;
    }
    public ListNode ReverseList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    //快慢指针找到中点+反转后半段
    public boolean isPalindrome2(ListNode head){
        ListNode half = head;
        ListNode cur = head;
        while(cur != null){
            half = half.next;
            if(cur.next != null) cur = cur.next.next;
            else break;
        }
        //经过上面的操作，half 就是指向了中间的节点，如果是偶数个节点,half就是中间靠右的节点
        //反转后面的
        ListNode back = ReverseList(half);
        while(head != null && back != null){
            if(head.val != back.val) return false;
            head = head.next;
            back = back.next;
        }
        return true;
    }
}
