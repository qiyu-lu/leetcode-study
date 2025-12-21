package com.leetcode.hot100.matrix;

public class _240_SearchMatrix {
    /**
     * 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(matrix[0][0] > target || matrix[m-1][n-1] < target) return false;
        int top = 0;
        int right = n-1;
        boolean res = false;
        while(top < m && right >= 0){
            //如果当前行最小值都大于目标值，就没有
            if(matrix[top][0] > target) return false;
            //如果当前行最大值大于目标值，就看次大值
            if(matrix[top][right] > target){
                right--;
                continue;
            }
            //这道题不能遍历，依靠单调性就可以不断排除某行某列
            //到这步 matrix[top][right] 为当前行小于等于目标值的元素
            if(matrix[top][right] == target) return true;
            //小于，则看下一行
            top++;
        }
        return res;
    }
    //找信息量最大的点，利用单调性，
    //左上角的点，如果x > target 则返回false， 如果 x < target 右边和下边都有可能
    //右下角的点，如果x < target 则返回false， 如果 x > targer 左边和上边都有可能
    //左下角的点，如果x > target 则
    public boolean searchMatrixV2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(matrix[0][0] > target || matrix[m-1][n-1] < target) return false;
        int top = 0;
        int right = n-1;
        while(top < m && right >= 0){
            if(matrix[top][right] == target) return true;
            else if (matrix[top][right] > target) {
                //当前值是列的最小值，大于目标值，所以整列不可能
                right--;
            }
            else{//当前值是行的最大值，小于目标值，所以整行不可能
                top++;
            }
        }
        return false;
    }
}
