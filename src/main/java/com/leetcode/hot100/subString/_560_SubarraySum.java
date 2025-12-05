package com.leetcode.hot100.subString;

public class _560_SubarraySum {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     * */
    public int subarraySumBF(int[] nums, int k) {
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            int j = i;
            while(j < nums.length){
                sum += nums[j++];
                if(sum == k) {
                    ans++;
                    //break;//之前这里有break，发现会报错，
                    /**
                     * [1,-1,0]  0  预期结果：3 有三个 【1 -1】 【1 -1 0】 【0】3组，如果break就少了 【1 -1 0】这组了
                     * */
                }
            }
        }
        return ans;
    }
}
