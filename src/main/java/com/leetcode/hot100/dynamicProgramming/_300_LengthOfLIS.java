package com.leetcode.hot100.dynamicProgramming;

import java.util.Arrays;

public class _300_LengthOfLIS {
    /**
     * 300 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * */

    //先模仿前面做题的思路，dp[i]就是前i个数中严格递增子序列的长度

    //正确的是；dp[i] = 以 nums[i] 结尾的最长严格递增子序列长度
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if(length == 0) return 0;
        int[] dp = new int[length];
        Arrays.fill(dp,1);
        int res = 1;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }
}
