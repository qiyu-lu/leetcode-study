package com.leetcode.hot100.dynamicProgramming;

import java.util.Stack;

public class _85_MaximalRectangle {
    /**
     * 85 最大矩形
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[] heights = new int[cols];
        for(int i = 0; i < rows; i++){
            //heights[j] =“在第 j 列，以当前行为底，连续向上有多少个 1”
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
    //84题的 柱状图中最大的矩形
    private int largestRectangleArea(int[] heights){
        int length = heights.length;
        if(length < 1) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < length; i++){
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int curIndex = stack.pop();
                int rightIndex = i;
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int curRectangle = (rightIndex - leftIndex - 1) * heights[curIndex];
                max = Math.max(max, curRectangle);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int curIndex = stack.pop();
            int rightIndex = length;
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int curRectangle = (rightIndex - leftIndex - 1) * heights[curIndex];
            max = Math.max(max, curRectangle);
        }
        return  max;
    }
}
