package com.leetcode.hot100.twoPointers;

public class _42_TrappinRainWater {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 思路：假设每个地方有一个水桶，
     * 水桶的左边依赖于左边最高的柱子的高度，右边依赖于右边最高的柱子的高度 然后均减去当前柱子格子的高度
     * 比如 1 0 2 这种情况，每个位置有一个水桶的话
     * 1--对应的水桶，左边高为 1-1，右边高为 2-1 水0
     * 0--对应的水桶，左边高为1-0，右边高为2-0 水1
     * 2--对应的水桶，左边高为2-2，右边高位2-2 水0
     * 那么水的体积 = 水桶的体积 = min(水桶的两个边)*1
     * */
    public int trapByArray(int[] height) {
        int n = height.length;
        int[] preMax = new int[n];//这个数组用于存放从当前位置向左数的最大高度，即为当前水桶的左边的有效高度
        preMax[0] = height[0];//第一个左边最大的就是当前的格子高度
        for(int i = 1; i < n; ++i){
            preMax[i] = Math.max(preMax[i-1], height[i]);
        }
        int[] sufMax = new int[n];//这个数组用于存放从当前位置向右数的最大高度，即为当前水桶的右边的有效高度
        sufMax[n-1] = height[n-1];//最末尾的水桶向右看最大的高度就是当前的格子高度
        for(int i = n - 2; i >= 0; --i){
            sufMax[i] = Math.max(sufMax[i+1], height[i]);
        }
        int ans = 0;//记录桶的大小，即水的容量
        //是水桶的左边和右边的最小值再减去当前格子的大小
        for(int i = 0; i < n; ++i){
            ans += Math.min(preMax[i], sufMax[i]) - height[i];
        }
        return ans;
    }
    //看能否去掉一个循环
    public int trapByArray1(int[] height) {
        int n = height.length;
        int[] sufMax = new int[n];//这个数组用于存放从当前位置向右数的最大高度，即为当前水桶的右边的有效高度
        sufMax[n-1] = height[n-1];//最末尾的水桶向右看最大的高度就是当前的格子高度
        for(int i = n - 2; i >= 0; --i){
            sufMax[i] = Math.max(sufMax[i+1], height[i]);
        }
        int ans = 0;//记录桶的大小，即水的容量
        int[] preMax = new int[n];//这个数组用于存放从当前位置向左数的最大高度，即为当前水桶的左边的有效高度
        preMax[0] = height[0];//第一个左边最大的就是当前的格子高度
        for(int i = 1; i < n; ++i){
            preMax[i] = Math.max(preMax[i-1], height[i]);
            ans += Math.min(preMax[i], sufMax[i]) - height[i];
        }
        return ans;
    }
    //双指针
    public int trapByTwoPointers(int[] height) {
        int n = height.length;
        int p1 = 0;
        int p2 = n - 1;
        int ans = 0;
        while(p1 < p2){
            if(height[p1] < height[p2]) p1++;
            else p2--;
        }
    }

}
