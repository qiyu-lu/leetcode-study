package com.leetcode.hot100.subString;

/**
 * 76.最小覆盖子串
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，
 * 使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * */
public class _76_MinWindow {
    //使用滑动窗口，使用两个指针，左指针负责收缩，右指针负责扩大，然后判断这个滑动窗口中的子串是否满足目标字符串的情况
    public String minWindow(String s, String t) {
        //大写字母从65-90 小写字母从97-122
        int[] need = new int[128];
        int[] window = new int[128];

        //记录目标字符的字母频率
        for(int i = 0; i < t.length(); i++){
            need[t.charAt(i)]++;
        }
        int needValid = 0;
        for(int num : need){
            if(num > 0) needValid++;//找到要求的字符种类数
        }


        int left = 0;
        int right = 0;
        int valid = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;//最短子串的起点
        while(right < s.length()){
            //扩张
            char c = s.charAt(right);
            right++;
            if(need[c] > 0){
                window[c]++;
                if(window[c] == need[c]){
                    valid++;
                }
            }

            //满足条件，进行收缩
            while(valid == needValid){

                if(right - left < minLen){
                    start = left;
                    minLen = right - left;
                }
                char d = s.charAt(left);
                left++;
                if(need[d] > 0){
                    if(window[d] == need[d]){
                        valid--;
                    }
                    window[d]--;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
