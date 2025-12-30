package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class _104_MaxDepth {
    /**
     * 104 二叉树的最大深度
     * 给定一个二叉树 root ，返回其最大深度。
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     * */
    public int maxDepth(TreeNode root) {
        if(root == null)  return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return  Math.max(left, right) + 1;
    }
    public int maxDepth2(TreeNode root) {
        dfs(root, 0);
        return this.ans;
    }
    int ans = 0;
    private void dfs(TreeNode root, int depth){
        if(root == null) return;
        depth++;

        ans = Math.max(ans, depth);
        dfs(root.left, depth);
        dfs(root.right, depth);
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){this.val = x;}
        TreeNode(int x, TreeNode left, TreeNode right){
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
