package com.leetcode.hot100.dynamicProgramming;

public class _198_Rob {
    /**
     * 198 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * */

    //来到第i间房，有两种选择：偷或者不偷
    //如果偷的话，那么说明i-1就不能偷，所以当前的最优结果是到第i-2为止的最大金额+当前房间的金钱
    //如果不偷的话，当前的最优结果是到第i-1间房间的最大金额
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[] max = new int[n];

        for(int i = 0; i < n; i++){
            if(i==0) max[i] = nums[i];
            else if(i == 1) max[i] = Math.max(nums[i], nums[i-1]);
            //从i=2，即第三间房开始进行下面的逻辑：
            //如果当前房间 i 偷的话，那么上一间 i-1 就不偷，最大金额是 到i-2 的最大金额，加上本间房间的金额
            //如果当前房间 i 不偷的话，那么最大金额是 到i-1 的最大金额
            else{
                //偷的情况
                int money1 = max[i-2] + nums[i];
                //不偷的情况
                int money2 = max[i-1];
                max[i] = Math.max(money1, money2);
            }
        }
        return max[n-1];
    }
}
