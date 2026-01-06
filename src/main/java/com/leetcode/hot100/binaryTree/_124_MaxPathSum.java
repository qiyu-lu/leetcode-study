package com.leetcode.hot100.binaryTree;

public class _124_MaxPathSum {
    /**
     * 124 二叉树中的最大路径和
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
     * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * */

    //题意类似 543 二叉树的直径
    public int maxPathSum(TreeNode root) {
        gain(root);
        return maxPathSum;
    }
    //题意类似 543 二叉树的直径 543 题要返回以 root 为根的子树的最大左子树深度 和 最大右子树深度 中的较大者，然后
    //得到当前root节点的直径

    //本题目要得到最大路径，返回 从 root 出发，向上（只能走一边）所能获得的最大路径和
    private int maxPathSum = Integer.MIN_VALUE;
    private int gain(TreeNode root){
        if(root == null) return 0;

        int left = gain(root.left);
        int right = gain(root.right);
        if(left<0) left=0;
        if(right<0) right=0;


        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        return Math.max(left, right) + root.val;
    }


    class TreeNode {
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
