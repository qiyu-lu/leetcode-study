package com.leetcode.hot100.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _438_findAnagrams {
    /**
     * 给定两个字符串 s 和 p，
     * 找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * */
    public List<Integer> findAnagramsBF(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = p.length();
        char[] pChars = p.toCharArray();
        Arrays.sort(pChars);
        for(int i = 0; i < s.length(); ++i){
            if(i + n > s.length()) return ans;
            char[] temp = s.substring(i, i + n).toCharArray();
            Arrays.sort(temp);
            if(Arrays.equals(temp, pChars)){ans.add(i);}
        }
        return ans;
    }
    public List<Integer> findAnagramsBySw(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = p.length();
        int [] cnt = new int[26];//用来进行标记
        for(int i = 0; i < n; ++i){
            cnt[p.charAt(i) - 'a']++;
        }
        int left = 0;
        int[] cntTemp = new int[26];
        int right = 0;
        for(; right < s.length();right++){
            while(right - left >= n){
                if(right - left == n && Arrays.equals(cnt, cntTemp) )//相等的话
                    ans.add(left);
                cntTemp[s.charAt(left) - 'a']--;//然后更新窗口
                left++;//无论是否是异位词子串的话都缩小窗口
            }
            cntTemp[s.charAt(right) - 'a']++;
        }
        if(right - left == n && Arrays.equals(cnt, cntTemp) )//最后到最后了还有一组没有判断
            ans.add(left);
        return ans;
    }
    public List<Integer> findAnagramsBySwV2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = p.length();
        int [] cnt = new int[26];//用来进行标记
        for(int i = 0; i < n; ++i){
            cnt[p.charAt(i) - 'a']++;
        }
        int left = 0;
        int[] cntTemp = new int[26];
        for(int right = 0; right < s.length();right++){
            cntTemp[s.charAt(right) - 'a']++;
            while (right - left + 1 >= n){
                if(right - left + 1 == n && Arrays.equals(cnt, cntTemp) )//相等的话
                    ans.add(left);
                cntTemp[s.charAt(left) - 'a']--;//然后更新窗口
                left++;//无论是否是异位词子串的话都缩小窗口
            }
        }
        return ans;
    }
    //只使用一个数组
    public List<Integer> findAnagramsBySwV3(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = p.length();
        int [] cnt = new int[26];//用来进行标记
        for(int i = 0; i < n; ++i) { cnt[p.charAt(i) - 'a']++; }
        int left = 0;

        for(int right = 0; right < s.length();right++){
           cnt[s.charAt(right) - 'a']--;//有如下几种情况
            /**
             * 如果 s.charAt(right) 这个字母在 p 字符串中存在的话，那么个数就减一了
             * 如果不存在，那么这个 s.charAt(right) 位置在cnt数组中就为负数了
             * */
            if(cnt[p.charAt(right) - 'a'] < 0)//为负数说明 这个字母不存在
            {//那就缩小窗口
                cnt[s.charAt(right) - 'a']++;//将这个字母的位置恢复为0
                cnt[s.charAt(left) - 'a']--;//这里缩小窗口
                left++;
            }
            if(right - left + 1 == n) ans.add(left);
        }
        return ans;
    }
}
