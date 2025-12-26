package com.leetcode.hot100.linkedList;

public class _19_RemoveNthFromEnd {
    /**
     * 19 删除链表的倒数第N个节点
     * 并返回链表的头节点
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        int cnt = n;
        while(cnt > 0){
            p2 = p2.next;
            cnt--;
        }
        //第一个指针指向某个节点，第二个指针指向第一个指针向后走n步的节点，
        //如果第二个节点是最后的节点，则第一个节点就是要删除节点的前面的那个节点。
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return dummy.next;
    }
}
