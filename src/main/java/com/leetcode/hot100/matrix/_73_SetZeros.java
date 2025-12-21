package com.leetcode.hot100.matrix;

public class _73_SetZeros {
    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，
     * 则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean rowFlag = false;
        boolean colFlag = false;
        //先观察第一行和第一列，看是否存在0，否则会和后续第一行第一列的置零混淆
        //第一列
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                colFlag = true;
                break;
            }
        }
        //第一行
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0){
                rowFlag = true;
                break;
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if(rowFlag){
            for(int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }
        if(colFlag)
            for(int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }

}
