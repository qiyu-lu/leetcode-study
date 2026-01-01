package com.leetcode.hot100.binaryTree;

public class _108_SortedArrayToBST {
    /**
     * 108 将有序数组转化为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，
     * 请你将其转换为一棵 平衡 二叉搜索树
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
        int length = nums.length;
        if(length==0) return null;

        return sortedArrayToBST(nums, 0, length-1);
    }
    private TreeNode sortedArrayToBST(int[] nums, int i, int j){
        if(i > j) return null;
        if(i == j) return new TreeNode(nums[i]);
        int mid = i + (j - i) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, i, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, j);
        return root;
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
