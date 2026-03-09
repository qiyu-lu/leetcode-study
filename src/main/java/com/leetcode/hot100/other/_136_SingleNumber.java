package com.leetcode.hot100.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 136.只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 * */
public class _136_SingleNumber {

    //直观的思路就是用一个哈希表来记录次数但是效率不是最好的
    public int singleNumberV1(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }
    //位运算，根据异或性质，相同数异或为0，任何数和0异或等于自身，异或满足交换律和结合律
    public int singleNumberV2(int[] nums) {
        int num = nums[0];
        for(int i = 1; i < nums.length; i++){
            num = num ^ nums[i];
            //因为异或满足交换率和结合律，那么可以将所有数进行异或操作
            //经过交换和结合，得到0 ^ 那个出现一次的数字
        }
        return num;
    }
}
