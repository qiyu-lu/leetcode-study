package com.leetcode.hot100.subString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _560_SubarraySum {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     * */
    public int subarraySumBF(int[] nums, int k) {
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            int j = i;
            while(j < nums.length){
                sum += nums[j++];
                if(sum == k) {
                    ans++;
                    //break;//之前这里有break，发现会报错，
                    /**
                     * [1,-1,0]  0  预期结果：3 有三个 【1 -1】 【1 -1 0】 【0】3组，如果break就少了 【1 -1 0】这组了
                     * */
                }
            }
        }
        return ans;
    }
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        //计算前缀和，参考303题的原理，通过前缀和数组来计算两个索引之间的元素和
        int[] pre = new int[n+1];
        pre[0] = 0;
        for(int i = 0; i < n; ++i) pre[i+1] = pre[i] + nums[i];
        //然后是求 和为 k 的子数组，那么就是两数之和，根据 303 题 假如 i <= j-1，[i, j-1] 之间包括端点的元素和为k，
        // 对应到前缀和数组中就是 [i, j]
        // s[j] - s[i] = k 那么 s[i] = s[j] - k, i 在j 的前面，那么就是遍历前缀和数组，看当前索引 j 之前的有没有等于 s[j] - k 的
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        int ans = 0;
        for(int j = 0; j < n + 1; ++j){
            if(map.containsKey(pre[j] - k)){
                ans += map.get(pre[j] - k);
                //list.add(new ArrayList<>(map.get(pre[j] - k) != j ? List.of(map.get(pre[j] - k), j) : List.of(j)));
                //然后更新这个哈希表 注意这个哈希表的值不是两数之和中的索引而是前面等于 s[j] - k 的个数

                //这部分放在这里更新的不全
//                if(map.containsKey(pre[j])) map.put(pre[j], map.get(pre[j]) + 1);
//                else map.put(pre[j], 1);

                /**
                 * 这里为什么不会漏掉 比如这个前缀数组是 0 1 1 2
                 * j=0,pre[j]=0,pre[j]-1=-1,哈希表中没有-1，加入键值对 (0,1)
                 * j=1,pre[j]=1,pre[j]-1=0,哈希表中有0，哈希表中对应的值是1，说明前面有1个0，。结果加上哈希表中0对应的值，ans = 1
                 *                          然后将当前值也加入哈希表，或者说更新值  表中：(0,1) (1,1)
                 * j=2,pre[j]=1,pre[j]-1=0,哈希表中有0，哈希表中对应的值是1，说明前面有1个0，。结果加上哈希表中0对应的值， ans = 2
                 *                          然后更新值  表中：(0,1) (1,2)
                 * j=3,pre[j]=2,pre[j]-1=1,哈希表中有1，哈希表中对应的值是2，说明前面有2个1，。结果加上哈希表中1对应的值， ans = 4
                 *                          然后更新值  表中：(0,1) (1,2) (2,1)
                 *
                 * 然后更新当前的值与没有加入键值对需要合并逻辑不然会报错
                 * */
            }
//            else{
//                map.put(pre[j], 1);
//            }
            if(map.containsKey(pre[j])) map.put(pre[j], map.get(pre[j]) + 1);
            else map.put(pre[j], 1);
        }
        return ans;
    }
}
