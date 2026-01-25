package com.leetcode.hot100.dynamicProgramming;

import java.util.Arrays;

public class _322_CoinChange {
    /**
     * 322 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * */
    //我的想法是类似第279 题，组成一个数的完全平方数的最少的个数，先梳理一下那道题的思路：
    //这个数可以先分为一个数和一个完全平方相加，然后这个数还可以继续分为一个数和完全平方数相加，所以这样的问题可以进行分解
    //大的问题可以分解为小的问题，
    //现在假设选了一个完全平方 k*k,然后在剩余的数 n-k*k 中再选一个完全平方，一直分解，那么就可以分解为最小的问题，

    //这道题如果是自己选则硬币面额大小和个数的话就和279题一样了，但是这道题的硬币面额是由输入的数组决定的。
    //129题的dp中初始化中的就是肯定是正确的个数，但是不是最少的
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(coin <= i && dp[i-coin] != -1){
                    if(dp[i] != -1) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                    else {
                        dp[i] = dp[i-coin] + 1;
                    }
                }
            }
        }
        return dp[amount] < 0 ? -1 : dp[amount];
    }
}
