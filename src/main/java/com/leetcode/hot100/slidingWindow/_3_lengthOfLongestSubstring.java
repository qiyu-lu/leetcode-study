package com.leetcode.hot100.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;

public class _3_lengthOfLongestSubstring {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * 思路：首先字符不重复的话需要哈希表的参与，
     * 然后需要采用滑动窗口的思想，
     * 用两个指针维护一个“窗口区间”，让窗口在数组/字符串上左右滑动，每次只移动一个指针（通常是右指针扩张，左指针收缩），从而避免重复遍历。
     * */
    public int lengthOfLongestSubstring(String s) {
//        if(s.length() < 2) return s.length();
        char[] arr = s.toCharArray();
        int n = arr.length;
        HashSet<Character> set = new HashSet<>();
        int ans = 0;
        int left = 0;
        int right = 0;

        while(right < n){
            if(!set.contains(arr[right])){
                set.add(arr[right]);
                right++;
                if(right >= n) ans = Math.max(ans, set.size());
            }
            else{
                ans = Math.max(ans, set.size());
                //ans = Math.max(ans, right-left);
                set.remove(arr[left]);
                left++;
            }
        }
        return ans;
    }
    /**
     * 利用这个提示： s 由英文字母、数字、符号和空格组成
     * 可以简化，ASCII 字符码范围：0~127，共 128 个字符
     * */
    public int lengthOfLongestSubstringByArray(String s) {
//        if(s.length() < 2) return s.length();
        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[] cnt = new boolean[128];
        int ans = 0;
        int left = 0;
        int right = 0;

        while(right < n){
            if(!cnt[arr[right]]){
                cnt[arr[right]] = true;
                right++;
                if(right >= n) ans = Math.max(ans, right-left);
            }
            else{
                ans = Math.max(ans, right-left);
                cnt[arr[left]] = false;
                left++;
            }
        }
        return ans;
    }
    public int lengthOfLongestSubstringByArrayPlus(String s) {
        int ans = 0;
        int left = 0;
        char[] arr = s.toCharArray();
        boolean[] has = new boolean[128];
        for(int right = 0; right < arr.length; ){
            while(has[arr[right]]){//为true时则说明之前出现过，使用while，就是不知道这个right位置的和什么位置的重复了
                has[arr[left]] = false;//缩小窗口，
                left++;
            }
            has[arr[right]] = true;
            right++;
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
