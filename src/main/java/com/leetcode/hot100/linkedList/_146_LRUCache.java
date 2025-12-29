package com.leetcode.hot100.linkedList;


import java.util.HashMap;
import java.util.Map;

public class _146_LRUCache {
    /**
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
     * 如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * */
    //LRU Cache 必须同时满足：
    //HashMap：key → 节点（O(1) 定位）
    //双向链表：维护“使用顺序”
    //每次 get / put，都要把节点移动到“最近使用位置”
    //淘汰的是“最久未使用的节点”（链表头）
    //节点里必须同时存 key 和 value

    Map<Integer, DNode> cache = new HashMap<>();
    int capacity;
    DNode headDummy;
    DNode tailDummy;//有哨兵节点就可以省去头节点和尾节点的使用了


    public _146_LRUCache(int capacity) {
        this.capacity = capacity;

        //空链表
        headDummy = new DNode(0,0);
        tailDummy = new DNode(0,0);

        headDummy.next = tailDummy;
        tailDummy.prev = headDummy;
    }
    public int get(int key) {
        if(this.capacity < 1 || !cache.containsKey(key)) return -1;
        DNode node = cache.get(key);

        deleteNode(node);
        //刚访问的节点就需要将其移动到链表尾
        updateTail(node);

        return node.val;
    }

    public void put(int key, int value) {
        if(this.capacity < 1 ) return;

        DNode curKeyNode = null;

        if(cache.containsKey(key)) {
            curKeyNode =  cache.get(key);

            deleteNode(curKeyNode);

            curKeyNode.val = value;
            updateTail(curKeyNode);
        }
        else{
            curKeyNode = new DNode(key, value);
            if(cache.size() == this.capacity){
                //需要清理链表头
                DNode oldHead = headDummy.next;
                cache.remove(oldHead.key);
                deleteNode(oldHead);

                //然后就将新节点插入到尾部
                updateTail(curKeyNode);
            }
            else{
                updateTail(curKeyNode);
            }
        }
        cache.put(curKeyNode.key, curKeyNode);
    }
    private void deleteNode(DNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void updateTail(DNode node){
        tailDummy.prev.next = node;
        node.prev = tailDummy.prev;

        node.next = tailDummy;
        tailDummy.prev = node;
    }
    class DNode{
        int key;
        int val;
        DNode next;
        DNode prev;
        DNode(int key, int val){
            this.key = key;this.val = val;
            this.next = null;this.prev = null;
        }
    }
}

