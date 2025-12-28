package com.leetcode.hot100.linkedList;

import java.util.HashMap;
import java.util.Map;

public class _138_CopyRandomList {
    /**
     * 138.随机链表的复制
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
     * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
     * 例如，如果原链表中有 X 和 Y 两个节点，
     * 其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     * */

    //建立新旧节点的关系映射，这里要记住map不仅可以存值，还可以存节点
    public Node copyRandomList(Node head) {
        if(head == null) return null;

        Node newDummy = new Node(0);
        Map<Node, Node> map = new HashMap<>();
        Node p1 = head;
        Node p2 = newDummy;
        while(p1 != null){
            p2.next = new Node(p1.val);
            p2 = p2.next;
            map.put(p1, p2);//新旧节点的对应关系

            p1 = p1.next;
        }
        p2.next = null;//next的指向创建了，

        //创建random 的指向
        p1 = head;
        p2 = newDummy.next;
        while(p1 != null){
            p2.random = map.get(p1.random);
            p2 = p2.next;

            p1 = p1.next;
        }
        return newDummy.next;
    }
    //第二种思路就是将新的插入到旧的链表中，来省去哈希表的空间
    public Node copyRandomListV2(Node head) {
        if(head == null) return null;

        Node newDummy = new Node(0);
        Node p1 = head;
        Node p2 = newDummy;

        while(p1 != null){
            Node p11 = p1.next;//保存旧节点之后的节点
            Node newNode = new Node(p1.val);//新节点
            p1.next = newNode;
            newNode.next = p11;

            p1 = p11;
        }//将新节点都插入到旧的链表中了

        //接下来就是调整新的random的指向了
        p1 = head;
        while(p1 != null){
            Node oldRandom = p1.random;
            p1.next.random = oldRandom == null ? oldRandom : oldRandom.next;//新的节点的random就是旧的random的下一个节点
            p1 = p1.next.next;//跳转到下一个旧的节点
        }
        //现在就是将新的节点进行提取出来
        p1 = head;
        while(p1 != null){
            Node oldNode = p1.next.next;
            p2.next = p1.next;
            p1.next = oldNode;

            //p1 = oldNode;
            p1 = p1.next;
            p2 = p2.next;
        }
        return newDummy.next;
    }
}
