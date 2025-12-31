package com.leetcode.hot100.binaryTree;

public class _101_IsSymmetric {
    /**
     * 101 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode a, TreeNode b){
        if(a == null && b == null) return true;
        if((a == null && b != null) || (a != null && b == null)) return false;
        if(a.val != b.val) return false;
        return isMirror(a.left,b.right) && isMirror(a.right,b.left);
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {this.val = x;}
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
