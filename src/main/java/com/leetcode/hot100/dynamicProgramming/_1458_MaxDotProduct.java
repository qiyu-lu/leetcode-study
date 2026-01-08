package com.leetcode.hot100.dynamicProgramming;

public class _1458_MaxDotProduct {
    /**
     * 1458 两个子序列的最大点积
     * 给你两个数组 nums1 和 nums2 。
     * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
     * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，
     * 但不能改变数字间相对顺序。比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
     * */
    //当你考虑 (i,j) 这一对元素时：
    //最优结果来自三类选择：
    //不用 nums1[i]
    //不用 nums2[j]
    //用 nums1[i] 和 nums2[j]
    //单独用
    //或接在之前的最优结果后面

    //举例子：nums1 = [2, 1]  nums2 = [3, -2]
    //先看 nums1[0] 和 nums2[0]，由于此时都只有1个数，只有一个选择，点积为 2 * 3 = 6
    //不用 nums1[0]和不用nums2[0]都不存在，然后用nums1[0]和nums2[0]的话也只有一种情况

    //看 nums1[0] 和 nums2[1]时，此时有如下情况：
    //nums1[0] 和 nums2[0]  2 * 3 = 6, nums1[0] 和 nums2[1] = 2 * -2 = -4 ,最优点积为 -4.
    //这里就可以看出可以省略的步骤，
    //不用nums1[0]的话，不存在这种情况，不用nums2[1]的话，就是 nums1[0]*nums2[0]，两个都用的话，

    //nums1[1] 和 nums2[0]时，此时有如下情况：
    //nums1[0] 和 nums2[0] = 2 * 3 = 6, nums1[1] 和 nums2[0] = 1 * 3 = 3，最优点积为 6

    //nums1[1] 和 nums2[1]时，此时有如下情况：
    //nums1[0] 和 nums2[0] = 2*3=6， nums1[0]*nums2[1]=-4,nums1[1]和nums1[0]= 3, nums1[1]*nums2[1]=1*-2=-2 nums1[0]*nums2[0] + nums1[1]*nums2[1]=4 最优点积为6
    //这里可以将其分类:
    //不用nums1[1]的情况，那么就是 nums1[0] 和 nums2[0] = 2*3=6，nums1[0]*nums2[1]=-4
    //不用nums2[1]的情况，那么就是 nums1[0] 和 nums2[0] = 2*3=6,nums1[1]和nums1[0]= 3,
    //用 nums1[1]和nums2[1]的话，有两种情况：单独用：nums1[1]*nums2[1]=1*-2=-2,在之前的最优结果后面加 nums1[0]*nums2[0] + nums1[1]*nums2[1]=4

    //现在使用一个二维矩阵来进行简化，来进行上面的可以省略的步骤，不用重复计算
    //        2      1
    //      ---------------
    // 3   | (0,0) (0,1)
    //-2   | (1,0) (1,1)

    //(0,0)位置放  nums1[0] 和 nums2[0] 的情况，
    //(0,1)的位置放nums1[0]和nums2[1]的情况，比较单独使用nums1[0]和nums2[1] 与 之前的 nums1[0]和nums2[0]的大小
    //即(0,0)

    //综上，(0,0)直接计算，(0,1)是单独计算的结果和(0,0)比较后的结果进行填充
    //(1,0)是直接计算和 (0,0)比较后的结果进行填充
    //（1，1）是单独计算的结果与 (0,1)、 (1,0)、 单独计算加上(0,0)的结果中的最大值
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // dp[i][j] 表示：
        // 在 nums1[0..i] 和 nums2[0..j] 中
        // 选 非空 子序列，能得到的最大点积
        int[][] dp = new int[m][n];

        // 初始化 dp 数组为一个很小的值（防止非法状态被选中）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        // 填表
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int product = nums1[i] * nums2[j];

                // 情况 1：单独使用 nums1[i] 和 nums2[j]
                dp[i][j] = product;

                // 情况 2：接在之前的最优结果后面
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + product);
                }

                // 情况 3：不用 nums1[i]
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }

                // 情况 4：不用 nums2[j]
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
