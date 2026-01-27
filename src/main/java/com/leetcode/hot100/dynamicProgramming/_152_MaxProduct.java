package com.leetcode.hot100.dynamicProgramming;

public class _152_MaxProduct {
    /**
     * 152 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），
     * 并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
     * */
    public int maxProduct(int[] nums) {
        int len = nums.length;

        //这个dp数组的含义是：按照第300题的思路，
        //dp[i] 代表着 nums[i] 为结尾的 最大乘积的 非空连续子数组的乘积。

        //因为有负数的存在，那么这里在进行判断时不仅要保存 nums[i] 为结尾的 最大乘积的 非空连续子数组的乘积。
        //还需要保存  nums[i] 为结尾的 最小乘积的 非空连续子数组的乘积。
        int[][] dp = new int[len][2];//0列放最大值，1列放最小值

        //这里应该不需要遍历前面的，因为要求是连续子数组
        //那么需要比较的有当前数，当前数和前一个数的乘积，当前数与前一个数和之前的乘积的乘积
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            if(i == 0){
                dp[i][0] = nums[i];
                dp[i][1] = nums[i];
            }
            else{
                dp[i][0] = Math.max(
                        nums[i],
                        Math.max(nums[i] * dp[i-1][0], nums[i] * dp[i-1][1])
                );

                dp[i][1] = Math.min(
                        nums[i],
                        Math.min(nums[i] * dp[i-1][0], nums[i] * dp[i-1][1])
                );
            }
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }
    //因为只会使用到前一个的状态，因此可以不使用数组来节省空间
}
