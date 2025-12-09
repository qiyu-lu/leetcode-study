package com.leetcode.hot100.subString;

public class _303_NumArray {
    /**
     * 560题目的 前置题目：前缀和模板题 303. 区域和检索 - 数组不可变
     * 给定一个整数数组  nums，处理以下类型的多个查询:
     *
     * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
     * 实现 NumArray 类：
     *
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
     * */
    //如果这样定义的话在求和时必须要进行判断，可以修改前缀数组的定义
//    public _303_NumArray(int[] nums) {
//        int sum = 0;
//        n = nums.length;
//        prefixSum = new int[n];
//        for(int i = 0; i < n; ++i){
//            sum += nums[i];
//            prefixSum[i] = sum;
//        }
//    }
//
//    public int sumRange(int left, int right) {
//        if(right >= n) right = n - 1;
//        if(left >= 1 && right < n)
//            return prefixSum[right] -  prefixSum[left - 1];
//        else return prefixSum[right];
//    }
    public _303_NumArray(int[] nums) {
        int sum = 0;
        n = nums.length + 1;
        prefixSum = new int[n];
        prefixSum[0] = sum;
        for(int i = 0; i < n - 1; ++i){
            sum += nums[i];
            prefixSum[i + 1] = sum;
        }
    }

    public int sumRange(int left, int right) {
        if(right >= n - 1) right = n - 2;
        if(left < 0) left = 0;
        return prefixSum[right + 1] -  prefixSum[left];
    }
    private int[] prefixSum;//这个成员存在的原因是如果根据一个现有的数组实例化一个对象的话，然后调用范围求和都要重新遍历的话就会浪费时间
    //可以通过这个成员来辅助实现求和
    private int n;
}
