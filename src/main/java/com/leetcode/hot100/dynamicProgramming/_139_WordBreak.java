package com.leetcode.hot100.dynamicProgramming;

import java.util.List;

public class _139_WordBreak {
    /**
     * 139 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * */

    //dp[i] = s 的前 i 个字符，是否可以被成功拼出来
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for(int i = 1; i <= len; i++){
            for(String word:wordDict){
                //如果我在位置 i，有没有一个单词 word，能让 dp[i - word.length] 为真，
                //且 s[i - word.length .. i) 正好等于 word？
                if(word.length() <= i
                && dp[i-word.length()]
                && s.substring(i - word.length(), i).equals(word)){
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }

}
