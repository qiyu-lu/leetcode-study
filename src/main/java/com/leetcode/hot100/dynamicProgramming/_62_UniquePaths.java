package com.leetcode.hot100.dynamicProgramming;

public class _62_UniquePaths {
    /**
     * 62 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * */
    //dp[i][j] = 走到 (i, j) 这个格子，一共有多少种走法
    //参考 70 题，走到 (i，j) 这个位置有以下方式：
    //从 (i-1, j) 向下走一格到 (i,j)
    //从 (i, j-1) 向右走一格到 (i,j)

    //所以 dp[i][j] = dp[i-1][j] + dp[i][j-1]
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return dfs(dp, 0, 0);
    }
    //从 (i,j) 走到终点位置有多少种走法
    private int dfs(int[][] dp, int i, int j) {
        if(i >= dp.length || j >= dp[0].length){
            return 0; //越界
        }
        if(i == dp.length - 1 && j == dp[0].length - 1){
            //已经到终点了，有0种走法
            return 1;
        }
        if(dp[i][j] != 0){
            //说明之前走过
            return dp[i][j];
        }
        dp[i][j] = dfs(dp, i+1, j) + dfs(dp, i, j+1);
        return dp[i][j];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
