package com.leetcode.hot100.linkedList;

public class _25_ReverseKGroup {
    /**
     * 25 k 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
     * 那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * */

    //不断进行三件事：
    //判断当前剩余的节点是否大于等于k
    //如果是，把前k个节点取出进行翻转
    //把翻转的这段接回原链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k < 2) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;

        while(cur != null){
            //从cur 开始走k步，找到tail，如果走不到k步说明剩下的不足k个，就保持原来的顺序
            ListNode tail = cur;
            for(int i = 1; i < k; i++){
                tail = tail.next;
                if(tail == null) return dummy.next;
            }
            ListNode temp = cur;
            ListNode next = tail.next;//明确下一段k节点的链表的起点

            //如果有k个节点的链表,那么 curr - tail 就是这个链表，左闭右闭
            ListNode newHead = reverserList(cur, tail);//将翻转的链表和之前的接上

            //重新接上
            pre.next = newHead;
            cur.next = next;//cur到了末尾了

            pre = temp;//反转前的cur就是这一段k个节点链表经过反转后的最后一个
            cur = next;
        }
        return dummy.next;
    }
    //翻转从 head 到 tail 的链表 闭区间 要求tail非空
    private ListNode reverserList(ListNode head, ListNode tail){
        ListNode pre = tail.next;
        ListNode cur = head;

        //之前使用的这个判断  while(cur != tail.next)  ，总是会错误，在处理过程中 tail 的指向会变的
        while(pre != tail){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        return pre;
    }

}
