package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_LevelOrder {
    /**
     * 102 二叉树的层序遍历
     * 给你二叉树的根节点 root ，
     * 返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //用 queue.size() 记录当前层的节点数
        while(!queue.isEmpty()){
            int length = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < length; i++){
                TreeNode cur = queue.poll();
                tmp.add(cur.val);
                if(cur.left != null)queue.add(cur.left);
                if(cur.right != null)queue.add(cur.right);
            }
            list.add(tmp);
        }
        return list;
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
