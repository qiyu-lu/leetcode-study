package com.leetcode.hot100.greedy;

public class _121_MaxProfit {
    /**
     * 121 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     * */
    //暴力遍历 超时了
    public int maxProfitBF(int[] prices) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++){
            for(int j = i + 1; j < prices.length; j++){
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max > 0 ? max : 0;
    }
    //正确的思路：在“卖出”的时候，只关心：到今天为止，最低的买入价是多少？
    //在第 i 天卖出股票时，最优的买入价，一定是 i 之前的最低价。
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l == 0) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < l; i++){
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }
}
