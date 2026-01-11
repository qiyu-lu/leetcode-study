package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39_CombinationSum {
    /**
     * 39 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
     * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * */
    /**
     * candidates = [2,3,6,7], target = 7
     *                        []
     *           2            3          6       7
     *      /   |  |  \    /  | \      /   \     |
     *      2  3  6   7   3  6  7    6     7     7     这里为了不重复，之前选过的就不能选了，
     *   / | |
     *
     * */
    //在一个组合中，每个数只能从当前数及其后面的数里选，这样就可以避免重复的问题
    //从 2 出发，下一层仍然可以选 2,从 3 出发，不能再回到 2
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target);
        return res;
    }
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void dfs(int[] candidate, int index,  int target){
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        else if(candidate[index] > target ||  target < 0) return;

        for(int i = index; i < candidate.length; i++){
            path.add(candidate[i]);
            dfs(candidate, i, target-candidate[i]);
            path.remove(path.size()-1);
        }
    }

    //优化后的：
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs2(candidates, 0, target);
        return res2;
    }
    private List<List<Integer>> res2 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

    private void dfs2(int[] candidate, int index,  int target){
        if(target == 0) {
            res2.add(new ArrayList<>(path2));
            return;
        }
        for(int i = index; i < candidate.length; i++){
            if(candidate[i] > target) break;
            path2.add(candidate[i]);
            dfs2(candidate, i, target-candidate[i]);
            path2.remove(path2.size()-1);
        }
    }
}
