package com.leetcode.hot100.binaryTree;

public class _543_DiameterOfBinaryTree {
    /**
     * 543 二叉树的直径
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     * */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter;
    }
    int diameter = 0;
    //梳理任务，对于经过某个节点的最长路径为
    //该节点左子树有多深 + 右子树有多深
    private int maxDepth(TreeNode root){
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        diameter = Math.max(diameter, left+right);
        return Math.max(left,right) + 1;
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){this.val = x;}
        TreeNode(int x, TreeNode l, TreeNode r){
            this.val = x;
            this.left = l;
            this.right = r;
        }
    }
}
