package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _131_Partition {
    /**
     * 131 分割回文串
     * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 示例 1：
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     * 示例 2：
     * 输入：s = "a"
     * 输出：[["a"]]
     * */
    //
    public List<List<String>> partition(String s) {
        if(s.length()==0){
            return new ArrayList<>();
        }
        dfs(s, 0);
        return res;
    }
    //题目要求每个分割的子串都为回文子串
    //将当前字符串分为两段，如果都不是回文串或者只有一段为回文串，那对左半段和右半段都进行分割递归判断
    //如果都是回文串加入结果集合中，再进行递归分割左右两半段，
    //这个分割为两段的部分怎么做，这样太复杂

    //正确的思路是从字符串左边开始，每次选择一个「回文前缀」，然后对剩余部分继续做同样的事。
    //递归语义是：从下标 start 开始，如何切割剩余字符串，使得每一段都是回文

    //切割点 end，[start, end) 和 (end, ...]
    //不断增加end，这样会切割为两段，然后判断时不是两段都进行判断，只判断 [start, end) 段是否为回文，
    //因为end是从小向大递增的，那么[start, end)是从1个字符开始递增的，不会发生遗漏的情况

    //[start, end)如果这段是回文，则加入当前路径，递归处理后半段，后半段的话也是从start开始，
    //如果不是回文则回溯
    private List<String> path =  new ArrayList<>();
    private List<List<String>> res = new ArrayList<>();
    private void dfs(String s, int start){
        //什么时候终止递归,
        if(start == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int end = start + 1; end <= s.length(); end++){
            String preStr = s.substring(start,end);
            if(isPalindrome(preStr)){//前半段是回文串
                path.add(preStr);
                dfs(s, end);
                //回溯要和添加承兑出现，
                path.remove(path.size()-1);
            }
            //else分支中没有添加就不必要进行回溯
        }
    }
    //判断回文 前半 vs 后半
    private boolean isPalindromeOld(String s){
        int n = s.length();
        if(n < 2) return true;

        //偶数 2/2 = 1 奇数 (3+1)/2=2
        int mid = (n+1)/2;
        int start = 0;
        while(mid < n){
            if(s.charAt(start) != s.charAt(mid)) return false;
            start++;
            mid++;
        }
        return true;
    }
    //合理的应该是左右两边进行收缩
    private boolean isPalindrome(String s){
        int n = s.length();
        if(n < 2) return true;

        int start = 0;
        int end = n - 1;
        while(start < end){
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}
