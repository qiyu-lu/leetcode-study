package com.leetcode.hot100.binaryTree;

public class _230_KthSmallest {
    /**
     * 230 二叉搜索树中第k小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
     * 请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
     * */
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }
    private int cnt = 0;
    private int res;
    private void inorder(TreeNode root, int k){
        if(root == null) return;
        inorder(root.left, k);
        //list.add(root.val);
        cnt++;
        if(cnt == k) res = root.val;
        inorder(root.right, k);
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
