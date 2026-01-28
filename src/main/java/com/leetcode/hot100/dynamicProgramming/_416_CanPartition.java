package com.leetcode.hot100.dynamicProgramming;

public class _416_CanPartition {
    /**
     * 416 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * */
    //dp[j] 代表着从数组 nums中能否找到几个数的和为 j，如果是true，则代表能找到几个数和为 j
    //目的是找到和为全部元素和的一半的情况

    //从前到后遍历输入数组：nums数组， 遍历到 num 这个数时
    //要判断 当前遍历的几个数中能不能找到几个数和为 j，那么可以转化为判断以前能不能凑出 j-num，
    //如果能，那么现在将 num 放入，就可以凑出 j，
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 == 1) return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;//挑选0个数就可以组成0
        for(int num : nums){
            //倒叙遍历是为了不多计算数，如果正序的话，当前循环之前一次变为true，会影响后续的变化
            for(int j = target; j >= num; j--){
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
