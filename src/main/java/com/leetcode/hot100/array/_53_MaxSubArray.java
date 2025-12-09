package com.leetcode.hot100.array;

public class _53_MaxSubArray {
    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组是数组中的一个连续部分。
     * */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int ans = 0;
        while(j < n){
            ans += nums[j];
        }
    }
}
