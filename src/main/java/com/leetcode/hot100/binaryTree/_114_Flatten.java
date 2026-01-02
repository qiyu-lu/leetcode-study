package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class _114_Flatten {
    /**
     * 114 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，
     * 其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * */
    public void flatten(TreeNode root) {
        preOrder(root);
        TreeNode dummy = new TreeNode(0);
        TreeNode p = dummy;
        for(TreeNode  n : list){
            p.right = n;
            n.left = null;
            p = p.right;
        }
        root = dummy.right;
    }
    private List<TreeNode> list = new ArrayList<>();
    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
