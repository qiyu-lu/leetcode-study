package com.leetcode.hot100.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 169.多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * 输入保证数组中一定有一个多数元素。
 * */
public class _169_MajorityElement {

    //第一个思路还是简单直观的哈希表
    public int majorityElementV1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > len/2){
                return entry.getKey();
            }
        }
        return 0;
    }

    //Boyer–Moore 投票算法（摩尔投票法）
    //想象一个过程，不同的数相互抵消，剩下的就是那个多数元素，多数元素数量 > 其他元素总数
    //维护两个变量：candidate   当前候选人 count       票数
    //遍历数组时，如果票数为0，将当前遍历的数设置为候选人
    // 当前遍历的数和候选人进行比较，如果相等，票数加一
    //如果不相等，则票数减一，相当于不相等相互抵消
    public int majorityElementV2(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                candidate = nums[i];
                count = 1;
            }
            else if(candidate == nums[i]){
                count++;
            }
            else{
                count--;
            }
        }
        return candidate;
    }
}
