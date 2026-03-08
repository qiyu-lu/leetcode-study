package com.leetcode.hot100.dynamicProgramming;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * */
public class _1143_LongestCommonSubsequence {
    //大问题可以拆成小问题
    //两个字符串text1 = abcde  text2 = ace
    //如果只看 abcd 和 ac 此时的结果是2：ac; 然后接下来的字符一样就在之前的基础上加一
    //定义状态 dp[i][j] 表示 text1 前 i 个字符，text2 前 j 个字符的最长公共子序列长度
    // dp[i][j]: 有两种情况,
    // text1 的第i个和 text2 的第j个字符相同, text1[i-1] == text2[j-1]  则 dp[i][j] = dp[i-1][j-1] + 1;
    // 如果不相等, 则 这两个字符不可能同时出现在公共子序列中，只能删除其中的一个
    // 删除 text1[i-1]:  dp[i][j] = dp[i-1][j]
    // 删除 text2[j-1]:  dp[i][j] = dp[i][j-1]，那么最终的 取其中的最大值
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
