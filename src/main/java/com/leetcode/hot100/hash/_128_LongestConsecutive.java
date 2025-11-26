package com.leetcode.hot100.hash;
import java.util.HashSet;

public class _128_LongestConsecutive {
    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 思路：遍历数组元素，将元素存入到哈希表中，然后遍历哈希表
     * */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums) {set.add(i);}
        int cnt = 0;
        for(Integer i : set){
            if(set.contains(i - 1)){//如果包含x-1,则从x-1开始找x, x+1, x+2,..
                continue;//跳过这个数,不是起点
            }
            else//如果不包含x-1,则从x开始找 x+1, x+2.., x 是对应的连续序列中最小的
            {
                int num = i + 1;
                while(set.contains(num)){
                    num++;
                } // 比如 4，3，2，1这个数组，  x=1， num为5时退出循环，总共 num - i = 4个连续数
                cnt = Math.max(cnt, num - i);
            }
        }
        return cnt;
    }
    public int longestConsecutivePlus(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums) {set.add(i);}
        int cnt = 0;
        int m = set.size();
        for(Integer i : set){
            if(set.contains(i - 1)){//如果包含x-1,则从x-1开始找x, x+1, x+2,..
                continue;//跳过这个数,不是起点
            }
            else//如果不包含x-1,则从x开始找 x+1, x+2.., x 是对应的连续序列中最小的
            {
                int num = i + 1;
                while(set.contains(num)){
                    num++;
                } // 比如 4，3，2，1这个数组，  x=1， num为5时退出循环，总共 num - i = 4个连续数
                cnt = Math.max(cnt, num - i);
                if(cnt * 2 > m) return cnt;
                //设 m 为 nums 中的不同元素个数（即哈希集合的大小）。各个连续序列（链）是互相独立的，如果我们发现其中一条链的长度至少为 m/2
                // （长度乘 2 大于等于 m），由于不可能还有一条长度大于 m/2的链（否则这两条链的长度之和就超过 m 了），答案不会再增大，
                // 此时可以直接返回答案。
            }
        }
        return cnt;
    }
}
