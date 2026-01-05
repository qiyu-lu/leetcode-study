package com.leetcode.hot100.binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _437_PathSum {
    /**
     * 437 路径总和
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，
     * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，
     * 也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * */
    //前缀和思路
    // 记录答案
    private int result = 0;

    public int pathSum(TreeNode root, int targetSum) {
        // map：前缀和 -> 出现次数
        // 含义：在「当前路径」上，某个前缀和出现了多少次
        Map<Long, Integer> prefixCount = new HashMap<>();

        // 非常关键的一步：
        // 前缀和为 0 出现 1 次
        // 用来处理「路径本身就等于 targetSum」的情况
        prefixCount.put(0L, 1);

        // 从根节点开始 DFS
        dfs(root, 0L, targetSum, prefixCount);

        return result;
    }

    /**
     * @param node 当前节点
     * @param curSum 从根到当前节点的路径和
     * @param target 目标和
     * @param prefixCount 当前路径上的前缀和统计表
     */
    private void dfs(TreeNode node,
                     long curSum,
                     int target,
                     Map<Long, Integer> prefixCount) {

        // 到达空节点，直接返回
        if (node == null) return;

        // 1️⃣ 更新当前路径和
        curSum += node.val;

        // 2️⃣ 查找：是否存在某个「祖先前缀和」
        // 使得：curSum - ancestorSum = target
        // 即：ancestorSum = curSum - target
        long need = curSum - target;

        // 如果存在，说明以当前节点为终点的合法路径有 prefixCount.get(need) 条
        result += prefixCount.getOrDefault(need, 0);

        // 3️⃣ 把当前前缀和加入 map（进入路径）
        prefixCount.put(curSum, prefixCount.getOrDefault(curSum, 0) + 1);

        // 4️⃣ 递归左右子树（继续向下）
        dfs(node.left, curSum, target, prefixCount);
        dfs(node.right, curSum, target, prefixCount);

        // 5️⃣ 回溯：离开当前节点，移除当前前缀和
        // 非常关键！保证 map 只保存「当前路径」的信息
        prefixCount.put(curSum, prefixCount.get(curSum) - 1);
    }

    //如果考虑递归的话 递归思路太过复杂
    //给递归函数一个根节点，找到当前根节点到最下面的子节点的节点和为目标值的数目
    //但是这样下一层返回的个数到这一层的话好像没法利用，
    //那要不要返回一个列表，列表里面放的是当前根节点到最下面的节点的节点值之和
    //然后个数的话从列表中数


    //dfs(node) 返回一个列表 包含所有“以 node 为结尾”的向下路径的和
    //这个结尾的意思是：如果一条向下的路径，要想经过我，并继续向上汇报，那它的当前和是多少？
    //在 node 这一层，你要做 3 件事：
    // 从左子树拿到一个列表
    // 从右子树拿到一个列表
    // 把它们 加上当前节点值 当前节点只有一个节点也可以作为一条路径
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
