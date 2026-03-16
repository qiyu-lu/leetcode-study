package com.leetcode.hot100.twoPointers;

public class _11_ContainerWithMostWater {
    /**
     * 11. 盛最多水的容器
     *
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，
     * 第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     *
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
