package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class _94_InorderTraversal {
    /**
     * 94 二叉树的中序遍历
     * */
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return res;
    }
    private void inorder(TreeNode root){
        if(root == null) return;
        //如果不为空
        //先看左子树
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
