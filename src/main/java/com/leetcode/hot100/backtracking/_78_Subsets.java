package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _78_Subsets {
    /**
     * 78 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * */
    //在46 全排列题目中，想的是当前位置我可以放谁，每个位置要从“剩余元素”里选一个
    //而这里，应该想的是当前这个元素，我要不要它
    //                         []
    //                /                     \
    //           不选1                        [1]
    //         /        \                 /        \
    //     不选2        [2]           [1]          [1,2]
    //     /    \       /   \          /   \          /   \
    //   []    [3]   [2]  [2,3]     [1] [1,3]     [1,2] [1,2,3]
    public List<List<Integer>> subsets(int[] nums) {
        int[] choose = new int[nums.length];
        dfs(nums, 0);
        return res;
    }
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    //递归的本质是从左到右，每个元素只处理一次，每次只做一个二选一的决定
    //递归的语义是：从下标 index 开始，决定 nums[index ... 末尾] 这些元素能形成的所有子集，
    //并且当前已经选好的元素在 path 里
    //
    private void dfs(int[] nums, int index) {
        if(index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        //不要当前索引的数，不用额外数组，直接跳过，进行下个索引的数
        dfs(nums,index+1);

        //要当前索引的数
        path.add(nums[index]);
        dfs(nums, index+1);
        //把 path 恢复到“进入这一层之前”的状态
        path.remove(path.size()-1);
    }
}
