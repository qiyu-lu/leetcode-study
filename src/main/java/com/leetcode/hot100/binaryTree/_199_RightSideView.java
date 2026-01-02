package com.leetcode.hot100.binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199_RightSideView {
    /**
     * 199 二叉树的右视图
     * 给定一个二叉树的 根节点 root，
     * 想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int tmp = 0;
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
                tmp = cur.val;
            }
            res.add(tmp);
        }
        return res;
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
