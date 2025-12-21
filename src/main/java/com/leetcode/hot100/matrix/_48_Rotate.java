package com.leetcode.hot100.matrix;

public class _48_Rotate {
    /**
     * 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int top = 0;
        int right = n-1;
        int bottom = n-1;
        int left = 0;
        while(top < bottom && left < right){
            //这里重大错误，循环时还是使用的固定的n
//            for(int i = 0; i < n - 1; i++){
//                int temp = matrix[top][i];
//                matrix[top][i] = matrix[n-1-i][left];
//                matrix[n-1-i][left] = matrix[bottom][n-1-i];
//                matrix[bottom][n-1-i] = matrix[i][right];
//                matrix[i][right] = temp;
//            }

            //总结一下
            //现在的正方形的边为：
            // [top,top]-[top,right] ,[top,right]-[bottom,right]
            // [bottom,right]-[bottom,left] [bottom,left]-[top,left]
            // 因此，点 [top,i]相关联的位置是
            // [top,top+i]-[top+i,right]-[bottom,right-i]-[bottom-i,left]
            for(int i = 0; i < right-top; i++){//不需要全部遍历，四个角上的不然会重复
                int temp = matrix[top][top+i];
                matrix[top][top+i] = matrix[bottom-i][left];
                matrix[bottom-i][left] = matrix[bottom][right-i];
                matrix[bottom][right-i] = matrix[top+i][right];
                matrix[top+i][right] = temp;
            }
            top++;right--;bottom--;left++;
        }
    }
}
