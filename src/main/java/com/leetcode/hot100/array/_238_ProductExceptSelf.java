package com.leetcode.hot100.array;

import java.util.Arrays;

public class _238_ProductExceptSelf {
    /**
     * 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 示例 1:
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     * 示例 2:
     * 输入: nums = [-1,1,0,-3,3]
     * 输出: [0,0,9,0,0]
     * */
    public int[] productExceptSelfBf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res,1);
        for(int i = 0; i<n; i++){
            for(int j = 0; j < n; j++){
                if(j==i) continue;
                res[i] *= nums[j];
            }

        }
        return res;
    }//暴力求解会超时
    //前缀乘积和后缀乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n+1];
        int[] suf = new int[n+1];
        pre[0] = 1;
        suf[n] = 1;
        for(int i = 0; i < n; i++){
            pre[i+1] = pre[i] * nums[i];
            suf[n-i-1] = suf[n-i] * nums[n-i-1];
        }
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            res[i] = pre[i] * suf[i+1];
        }
        return res;
    }
    //考虑不使用额外空间
    public int[] productExceptSelfPro(int[] nums) {
        int n = nums.length;
        int pre = 1;
        int suf = 1;
        int[] res = new int[n];
        //如果不使用额外的数组存放前缀乘积和后缀乘积结果的话，
        //就需要一边计算前缀和后缀一边计算结果
        //但是前缀和后缀好像不能同时算来，
        // 那么先尝试将前缀乘积存入结果数组中
        for(int i = 0; i < n; i++){
            res[i] = pre;
            pre *= nums[i];
        }
        for(int i = n - 1; i > -1; i--){
            res[i] = res[i] * suf;
            suf *= nums[i];
        }
        return res;
    }
}
