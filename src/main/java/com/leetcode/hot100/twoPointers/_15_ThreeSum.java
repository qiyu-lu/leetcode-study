package com.leetcode.hot100.twoPointers;

import java.util.*;

public class _15_ThreeSum {
    /**
     * 请你返回所有和为 0 且不重复的三元组,三个数在数组中的索引互不相等，且和为0的三元组
     * 思路一：固定一个数，在剩下的元素中进行找到两个元素和等于target的 暴力求解
     * 思路二：为方便双指针以及跳过相同元素，先把 nums 排序，此时数组变为有序的数组，遍历每个数
     * （实际不必要每个，后两个不需要遍历，因为要求输出三元数组）
     * 在外层循环中，如果发现 nums[i]=nums[i−1]，此时已经经过排序处理了，
     * 那么 nums[i] 与后面两个数组成的和为 0 的三元组，nums[i−1] 也能组成一模一样的三元组，这就重复了，所以遇到 nums[i]=nums[i−1] 的情况，直接 continue。
     * 在内层循环中，当三数之和等于 0 时，为避免把相同的三元组计入答案，跳过后续相同的 nums[j] 和 nums[k]（也可以只跳过相同的 nums[j]）。
     * */
    public List<List<Integer>> threeSumBF(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        HashSet<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < nums.length; ++i){
            HashSet<Integer> set = new HashSet<>();
            for(int j = i + 1; j < nums.length; ++j){
                int key = 0 - nums[i] - nums[j];
                if(set.contains(key)){
                    List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], key));
                    Collections.sort(temp);
                    result.add(temp);
                }
                else{
                    set.add(nums[j]);
                }
            }
        }
        return new ArrayList(result);
    }
    public List<List<Integer>> threeSumTwoPointers(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n - 2; ++i){
            if(i > 0 && nums[i] == nums[i - 1]) continue;//如果相等说明此时组成的三元组，和前面的会重复
            if(nums[i] + nums[i+1] + nums[i+2] > 0) break;//当前遍历的起点开始 最小的三元数和大于0，后面的就没有了
            if(nums[i] + nums[n-1] + nums[n-2] < 0) continue;//当前遍历数开始的最大的数的和小于0，跳过本次循环，查询下个数
            int p1 = i + 1;
            int p2 = n - 1;
            while (p1 < p2){
                if(nums[i] + nums[p1] + nums[p2] == 0) {
                    list.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    //此时满足条件，那么之后的重复的数就可以跳过了  这里必须要这么做，不然会出现内存超过限制的错误爆出
                    for(p1++; p1 < p2 && nums[p1] == nums[p1-1]; p1++);
                    for(p2--; p2 > p1 && nums[p2] == nums[p2+1]; p2--);
                }
                else if(nums[i] + nums[p2] + nums[p1] > 0) //注意此时数组已经排序过了，不需要两层循环遍历
                    //大于0 向小的方向移动
                    p2--;
                else p1++;
            }
        }
        return list;
    }
}
