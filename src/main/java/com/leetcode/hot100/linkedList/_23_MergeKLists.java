package com.leetcode.hot100.linkedList;

public class _23_MergeKLists {
    /**
     * 23 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * */
    //直接逐个遍历合并：参考21题的思路
    public ListNode mergeKListsV1(ListNode[] lists) {
        int len = lists.length;
        if(len == 0) return null;
        if(len == 1) return lists[0];
        ListNode res = lists[0];
        for(int i = 1; i < len; i++){
            res = merge(res, lists[i]);
        }
        return res;
    }
    //上面的方法一v1版本，第一个链表被重新比较了多次，不均匀
    public ListNode mergeKListsV2(ListNode[] lists) {
        return mergeKListsV2Fuc(lists, 0, lists.length);
    }
    private ListNode mergeKListsV2Fuc(ListNode[] lists, int i, int j){
        int cnt = j - i;
        if(cnt == 0) return null;
        if(cnt == 1) return lists[i];
        ListNode left = mergeKListsV2Fuc(lists, i, i + cnt / 2);
        ListNode right = mergeKListsV2Fuc(lists, i + cnt/2, j);
        return merge(left, right);
    }
    private ListNode merge(ListNode p1, ListNode p2){
        if(p1 == null) return p2;
        if(p2 == null) return p1;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;

        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                pre.next = p1;
                p1 = p1.next;
                pre = pre.next;
            }
            else{
                pre.next = p2;
                p2 = p2.next;
                pre = pre.next;
            }
        }
        if(p1 != null) pre.next = p1;
        if(p2 != null) pre.next = p2;
        return dummy.next;
    }
}
