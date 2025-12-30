package com.leetcode.hot100.binaryTree;

public class _226_InvertTree {
    /**
     * 226 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {this.val = x;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
