package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1161_MaxLevelSum {
    /**
     * 1161 最大层内元素和
     * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     * 返回总和 最大 的那一层的层号 x。如果有多层的总和一样大，返回其中 最小 的层号 x。
     * */
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Long> sum = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            long curSum = 0;
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
                curSum += cur.val;
            }
            sum.add(curSum);
        }
        long maxVal = sum.get(0);
        int maxIndex = 0;
        for(int i = 1; i < sum.size(); i++){
            if(sum.get(i) > maxVal){
                maxVal = sum.get(i);
                maxIndex = i;
            }
        }
        return maxIndex + 1;
    }
    //能不能不使用列表
    public int maxLevelSumV2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxIndex = 0;
        long maxVal = Long.MIN_VALUE;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            long curSum = 0;
            cnt++;
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
                curSum += cur.val;
            }
            if(curSum > maxVal){
                maxVal = curSum;
                maxIndex = cnt;
            }
        }

        return maxIndex;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
