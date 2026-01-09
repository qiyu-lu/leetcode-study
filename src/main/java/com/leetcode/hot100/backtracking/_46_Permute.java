package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _46_Permute {
    /**
     * 46 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * */
    //把“全排列”想成一棵树 以 [1,2,3] 为例：
    //                []
    //         /        |        \
    //       [1]       [2]       [3]
    //     /     \     /    \     /    \
    // [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]
    //    |       |      |       |      |
    //[1,2,3] [1,3,2] ...
    //每一层选 一个还没用过的数 、路径长度 = nums.length、到叶子节点时，得到一个完整排列
    public List<List<Integer>> permuteBF(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, used);
        return res;
    }
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    //递归函数的语义是：在当前已经选了一部分数字的前提下，把剩下的数字继续排列，直到形成一个完整排列。
    private void dfs(int[] nums, boolean[] used){
        //path存放当前节点的路径，即从根节点到最下面的子节点的路径
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        //使用used数组标志这个数是否被使用，从没有被使用的数中挑选
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums,used);

            //进行回溯
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
