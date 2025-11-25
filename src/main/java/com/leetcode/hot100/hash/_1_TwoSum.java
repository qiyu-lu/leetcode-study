package com.leetcode.hot100.hash;

import java.util.HashMap;
import java.util.Map;

public class _1_TwoSum {
    public int[] twoSumBrute(int[] nums, int target) {
        for(int i = 0; i < nums.length; ++i){
            for(int j = i + 1; j < nums.length; ++j){
                if(nums[i] + nums[j] ==target) return new int[]{i, j};
            }
        }
        return null;
    }
    // 解法2：哈希表（高效）
    /**
     * map 常用操作：
     * map.put(key, value);            // 插入 / 更新
     * map.get(key);                   // 获取值（不存在返回 null）
     * map.containsKey(key);           // 判断是否有 key
     * map.remove(key);                // 删除
     * map.size();                     // 元素个数
     * map.getOrDefault(key, 0);       // 获取不存在时给默认值*/
    public int[] twoSumHash(int[] nums, int target) {
        Map<Integer , Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
