package com.leetcode.hot100.twoPointers;

import java.util.ArrayDeque;
import java.util.Deque;

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
    /**
     * 相向双指针的思路就是左右两个指针就表示着水桶可能的两个边的高度，
     * 假设现在已经计算出了一部分前缀的最大值，一部分后缀的最大值
     * 比如假设：  [0,1,0,2,1,0,1,3,2,1,2,1]
     *                i       j
     * 假设 左右指针 i， j分别遍历到这个位置，
     * i 左边的最大高度是 1 ，说明 i 这个位置上的水桶 的左边 最大高度是1，
     * j 右边的最大高度是 3 ，说明 j 这个位置上的水桶 的右边 最大高度是3，
     * 那么 i 这个位置上的水桶 左边最大的高度是 1 ，虽然中间还有没有遍历的，但右边已经遍历出的最大高度是3，
     * 比 i 这个位置上左边最大高度大，说明不会出现水流出的这种情况，就是可以存住水。i这个位置的水量就确定了
     * 那么之后 i 这个位置就可以右移了 因为 i 这个位置可以存的水就可以确定了，存水量由最低的边决定。
     *
     *     j 这个位置上的水桶，左边最大的高度至少为1，右边最大高度是 3 已经遍历了，3比1大，
     * 而中间还有没有遍历的，不确定是否能围住水，那么 j这个位置的水还不能确定，j 这个指针就不能移动。
     *
     * 综上，就是 左右指针，当确定了左右两边中低的一边时，即左边最大高度和右边最大高度中较低值确定，
     * 该位置的水量就可以确定了，这个指针就可以移动了。
     * */
    public int trapByTwoPointers(int[] height) {
        int n = height.length;
        int i = 0;//左指针用来找左边的最大值
        int j = n - 1;//右指针用来找右边的最大值
        int preMax = height[i];//存左边的最大值
        int sufMax = height[j];//右边最大值
        int ans = 0;
        while(i < j){
            if(preMax <= sufMax){
                ans += preMax - height[i];
                i++;
                preMax = Math.max(preMax, height[i]);
            }
            else{
                ans += sufMax - height[j];
                j--;
                sufMax = Math.max(sufMax, height[j]);
            }
        }
        return ans;
    }
    /**
     * 单调栈思想，没想明白
     * */
    public int trapByStack(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!st.isEmpty() && height[st.peek()] <= h) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH; // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }

}
