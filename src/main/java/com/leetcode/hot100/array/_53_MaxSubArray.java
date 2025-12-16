package com.leetcode.hot100.array;

public class _53_MaxSubArray {
    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组是数组中的一个连续部分。
     * */
    /**
     * 方法一：前缀和+贪心
     * 子数组的元素和等于两个前缀和的差，所以求出前缀和，然后一边遍历数组计算前缀和，一边计算当前的和最大值
     * 是当前前缀和，与 之前的 前缀和中最小值的差就是当前位置的连续子数组的最大和
     * 原数组：  [-2, 1,-3,4,-1,2,1,-5,4]
     * 前缀和：[0,-2,-1,-4,0,-1,1,2,-3,1]
     * 遍历原数组：
     * -2：当前前缀和-2，之前的最小前缀和是0，当前的子数组和最大值 -2
     * 1：当前前缀和-1，之前的最小前缀和是-2，当前的子数组和最大值 -1- （-2） = 1
     * */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n+1];
        pre[0] = 0;
        int preMin = 0;
        int ans = nums[0];
        for(int i = 0; i < n; i++){
            pre[i+1] = pre[i] + nums[i];
            ans = Math.max(ans, pre[i+1] - preMin);
            preMin = Math.min(preMin, pre[i+1]);
        }
        return ans;
    }
}
