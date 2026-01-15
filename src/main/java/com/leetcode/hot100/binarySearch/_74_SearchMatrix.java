package com.leetcode.hot100.binarySearch;

public class _74_SearchMatrix {
    /**
     * 74 搜索二维矩阵
     * 给你一个满足下述两条属性的 m x n 整数矩阵：
     * 每行中的整数从左到右按非严格递增顺序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        //我的思路是先找到这个数在哪一行中
        int i = 0;
        boolean flag = false;
        for(; i < m; i++){
            if(matrix[i][0] <= target && matrix[i][n-1] >= target) {
                flag = true;
                break;
            }
        }
        //判断目标数是否在当前行中
        return flag && isExist(matrix[i], target);
    }
    //判断一个数是否在一个递增的有序数组中
    private boolean isExist(int[] nums, int target){
        int n = nums.length;
        if(target < nums[0] || target > nums[n-1]) return false;

        int left = 0;
        int right = n-1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return true;
            else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        //下面的这个判断是多余的，当出了循环时说明left已经越界了，已经可以说明这个数不在数组中
        //        if(nums[left] == target) return true;
//        return false;
        return false;
    }
}
