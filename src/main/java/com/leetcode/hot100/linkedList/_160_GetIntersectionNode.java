package com.leetcode.hot100.linkedList;

public class _160_GetIntersectionNode {
    /**
     * 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * */
    public ListNode getIntersectionNodeV1(ListNode headA, ListNode headB) {
        int i = 0;
        int j = 0;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1.next != null && p2.next != null){//两个链表都没有走到终点
            p1 = p1.next;
            p2 = p2.next;
            i++;j++;
        }
        //当某一个链表走到终点时跳出上面的循环
        //现在开始然后另一个没走到终点的链表继续走
        while(p1.next != null){//第一个链表没走完,第二个链表走完了
            p1 = p1.next;
            i++;
        }
        while(p2.next != null){//第二个链表没走完，第一个链表走完了
            p2 = p2.next;
            j++;
        }
        //现在两个链表都走完了，headA 和 headB分别为两个链表的末节点
        if(p1 != p2) return null;//如果两个链表没有相交，逐个走过两个链表不会到达同一个节点

        //遍历完两个链表发现走到同一个节点了，说明肯定是相交了，那么如何找到这个相交的节点，
        // 笨办法，找到两个链表的长度差，处理一下从相同长度的起点出发，哪个相同就是交点
        //第一个链表长，那就处理第一个链表
        while(i > j && headA.next != null){
            headA = headA.next;
            i--;
        }
        while(j > i && headB.next != null){
            headB = headB.next;
            j--;
        }
        while(headA != headB && headA.next != null && headB.next != null){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    //就是假设如果不相交也相交了，相交在 null 节点处，
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 1. 计算两个链表长度
        int lenA = 0, lenB = 0;
        ListNode pA = headA, pB = headB;

        while (pA != null) {
            lenA++;
            pA = pA.next;
        }
        while (pB != null) {
            lenB++;
            pB = pB.next;
        }

        // 2. 长链表先走 |lenA - lenB| 步
        pA = headA;
        pB = headB;

        if (lenA > lenB) {
            int diff = lenA - lenB;
            while (diff-- > 0) pA = pA.next;
        } else {
            int diff = lenB - lenA;
            while (diff-- > 0) pB = pB.next;
        }

        // 3. 同步前进，第一次相等就是交点
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }

        return pA; // 可能是交点，也可能是 null
    }

    //代码的思路就是走相同长度 设两条链表的长度分别为 l1 ,l2,
    //如果相交则说明有交点，即有相同长度的后半段，l1 = x + m; l2 = y + m;
    //现在的话如果相交，则 p1 走过的长度是 ： x+m+y; p2 走过的长度是 y+m+x,长度相等
    //如果没有相交，则 p1和p2 走过的长度是 l1 + l2,
    public ListNode getIntersectionNodeV3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode p1 = headA, p2 = headB;
        while(p1 != p2){
            if(p1 == null) p1 = headB;
            else p1 = p1.next;

            if(p2 == null) p2 = headA;
            else p2 = p2.next;
        }
        return p1;
    }
}
