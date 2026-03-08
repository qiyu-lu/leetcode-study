package com.leetcode.hot100.dynamicProgramming;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * */
public class _72_MinDistance {

    //这道题我觉得可以直接使用 1143 题的代码，找到最长公共子串，然后用最长的字符串长度减去这个公共子串长度
    //但是结果不对，正确的是不仅要找出公共子串，而且还和位置有关系。

    //我试着自己思考将大的问题化解为小的问题
    //dp[i][j] 是 word1 的前 i 个字符 转化为 word2 的前 j 个字符所使用的最少操作数，要得到 dp[i][j]
    //假如 word1[i-1] == word2[j-1]， dp[i][j] = dp[i-1][j-1]
    //如果不相等，是不是就是 dp[i-1][j] 和 dp[i][j-1] 中较小的一个 然后加一

    public int minDistanceError(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
    //上面的代码只考虑了插入和删除两种情况
    // 要求 dp[i][j]：word1前i个字符转化为word2前j个字符的最少步骤数，如果word1[i-1] 和 word2[j-1] 不相等的话，
    //上面的代码只考虑了这两种情况：
    //删除：先把 word1 的前 i-1 个字符变成 word2 的前 j 个字符，然后 删除 word1[i-1]：dp[i][j] = dp[i-1][j] + 1
    //dp[i-1][j]   已经完成：
    //word1前 i-1 个字符 → word2前 j 个字符
    //再删除 word1[i-1]


    //插入：先把 word1 的前 i 个字符变成 word2 的前 j-1 个字符，然后 插入 word2[j-1] ：dp[i][j] = dp[i][j-1] + 1
    //dp[i][j-1] 已经完成：
    //word1前 i 个字符 → word2前 j-1 个字符
    //然后插入：word2[j-1]

    //替换：将 word1[i-1] 末尾的位置替换为 word2[j-1] : dp[i][j] = dp[i-1][j-1] + 1
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1);
                }
            }
        }
        return dp[len1][len2];
    }

}
