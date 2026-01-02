package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class _98_IsValidBST {
    /**
     * 98 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 严格小于 当前节点的数。
     * 节点的右子树只包含 严格大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * */
    public boolean isValidBSTV1(TreeNode root) {
        inorder(root);
        return flag;
    }
    private boolean flag = true;
    private List<Integer> list = new ArrayList<>();
    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        if(!list.isEmpty() && root.val <= list.get(list.size()-1)) flag = false;
        list.add(root.val);
        inorder(root.right);
    }
    //把列表换位一个变量
//    private boolean flag = true;
//    private long pre = Long.MIN_VALUE;
//    private void inorder(TreeNode root){
//        if(root == null) return;
//        inorder(root.left);
//        if(root.val <= pre ) flag = false;
//        pre = root.val;
//        inorder(root.right);
//    }
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
