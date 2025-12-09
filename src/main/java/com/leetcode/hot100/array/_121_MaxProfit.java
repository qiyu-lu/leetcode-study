package com.leetcode.hot100.array;

public class _121_MaxProfit {
    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * [2 4 1]
     * minPrice  2     2          2与
     * ans     2-2=0   0与4-2=2
     * */
    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = prices[0];
        for(int price : prices){
            ans = Math.max(ans, price - minPrice);//为假如当天卖的话的利润是多少
            minPrice = Math.min(minPrice, price);
        }
        return ans;
    }
}
