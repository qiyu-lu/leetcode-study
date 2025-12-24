package com.leetcode.hot100.linkedList;

public class _142_DetectCycle {
    /**
     * 142 环形链表，返回环起点的索引
     * */
    public ListNode detectCycle(ListNode head) {
        //设环形区域的链表中有 n 个节点
        //设从 head 到入环点长度为a，从入环点到相遇点，慢指针走了b长，
        //那么慢指针走的长度为 a + b
        //那么快指针走的长度为 a + b + k * n ； k为一个未知数，
        //结合速度关系推导得出 a + b + k * n = 2（a + b）
        //环长是 b+c ，所以有
        // s = a + b ; 2s = a + b + k(b + c)
        //化简得到： a = k(b + c) - b
        //两个节点一个从head走，一个从快慢节点的相遇点走，速度一致，一次走一个，
        //如果从head走的节点进环了，那么这个上面等号左右两边同时加几圈都不改变其相对位置，
        ListNode slow = head;
        ListNode fast = head;
        int cnt = 0;
        while(fast!= null && fast.next != null) {
            slow = slow.next;
            if(fast.next == null) return null;
            fast = fast.next.next;
            if(slow == fast) {//到达相遇点了
                while(slow != head){
                    slow = slow.next;
                    head = head.next;
                    cnt++;
                }
                return head;
            }
        }
        return  null;
    }
}
