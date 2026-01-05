package com.leetcode.hot100.array;

public class _1975_MaxMatrixSum {
    /**
     * 1975 最大方阵和
     * 给你一个 n x n 的整数方阵 matrix 。你可以执行以下操作 任意次 ：
     * 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。
     * 如果两个元素有 公共边 ，那么它们就是 相邻 的。
     * 你的目的是 最大化 方阵元素的和。请你在执行以上操作之后，返回方阵的 最大 和。
     * */
    //相邻的两个元素乘-1，一正一负变为一正一负，两正变为两负，两负变为两负，
    //所以经过一次变换后，负数的个数变化只有三种可能， +2 -2 0
    //得到结论，负数个数的奇偶性不变，原来是奇数经过变化后还是奇数，原来是偶数经过变化后还是偶数
    //定义变量：
    //sumAbs：所有元素 绝对值之和
    //negCnt：初始矩阵中 负数的个数
    //minAbs：矩阵中 绝对值最小的那个数

    //情况一：原来的矩阵中负数的个数是偶数：negCnt是偶数，那么就可以通过操作，把所有的数变为正数，输出 sumAbs
    //情况二：negCnt是奇数，那么最后一定会剩下一个负数，那么为了让和最大，就需要让这个负数绝对值最小
    //那么此时的输出是 最大和 = sumAbs - 2 * minAbs
    //因为：（因为本来它算成 +minAbs，现在变成 -minAbs，相差 2*minAbs）
    //现在设定 Ak 是绝对值最小的数，如果 Ak 是正数，那么全是正数， 最大和 = sumAbs = A1 + A2 + ... + Ak + ,,,,
    //现在 Ak 是负数，最大和 = sumAbs - |Ak| = A1 + A2 + ... + |Ak| + ,,,, - |Ak| - |Ak| = sumAbs -2 * minAbs
    //减去一个是 剩下的全是正数的 和， 减去两个是所有矩阵的和，
    public long maxMatrixSum(int[][] matrix) {
        long sumAbs = 0;
        int negCnt = 0;
        long minAbs = Long.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int v = matrix[i][j];
                if (v < 0) negCnt++;
                long abs = Math.abs((long) v);
                sumAbs += abs;
                minAbs = Math.min(minAbs, abs);
            }
        }

        if (negCnt % 2 == 0) {
            return sumAbs;
        }
        return sumAbs - 2 * minAbs;
    }

}
