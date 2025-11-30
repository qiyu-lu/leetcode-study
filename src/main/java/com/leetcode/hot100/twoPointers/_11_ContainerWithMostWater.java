package com.leetcode.hot100.twoPointers;

public class _11_ContainerWithMostWater {
    /**
     * 思路：一个指针从数组左边开始，一个指针从数组右边开始，始终移动较低高度的指针
     * 面积就是 min{height[i], height[j]} * (j - i)
     * 要始终保证 i 在 j 的左边 i < j
     * */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int area = Math.min(height[i], height[j]) * (j - i);
        while(i < j){
            area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
            if(height[i] <= height[j]) i++;
            else j--;
        }
        return area;
    }
}
