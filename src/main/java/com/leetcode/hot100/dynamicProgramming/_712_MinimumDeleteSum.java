package com.leetcode.hot100.dynamicProgramming;

public class _712_MinimumDeleteSum {
    /**
     * 712 两个字符串的最小ASCII删除和
     * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
     * */
    //一开始的想法是先排序，后考虑删除的事，但是不可行，排序会导致结果错了，
    //比如 delete 和 leet，正常是删除 dee 和 ee 为 lt，
    //排序后分别为 e e e d l t 和 e e l t会判断删除ed，输出为 eelt这不是正确的答案

    //然后的想法是先去掉字符串1中的在字符串2中不存在的字符，去掉字符串2中字符串1中不存在的字符

    //问了ai后：
    //错误思维：我要怎么删，才能代价最小 这种想法会立刻陷入组合爆炸。
    //正确思路：最终两个字符串留下来的那部分，一定是一个公共子序列，而且是ASCII 总和尽量大的公共子序列
    //假设字符串 s1 的所有字符的ascii码和为 n =a+b,s2的ascii码和为 m =b+c,
    //其中 b是公共子序列的ascii码和，现在要求删掉的字符ASCII码最小和，即a+c最小，
    //那么就要求 n+m 一定时， b最大时 a+c 最小

    //问题等价于：找一个 s1 和 s2 的公共子序列使它的 ASCII 总和最大

    //定义状态：dp[i][j]：s1 的前 i 个字符和 s2 的前 j 个字符的公共子序列 最大 ASCII 和
    //现在看 s1[i-1] 和 s2[j-1]
    //如果相等：那么就将这个字符留下了
    public int minimumDeleteSum(String s1, String s2) {

        int l1 = s1.length();
        int l2 = s2.length();

        int[][] dp = new int[l1+1][l2+1];

        ////定义状态：dp[i][j]：s1 的前 i 个字符和 s2 的前 j 个字符的公共子序列 最大 ASCII 和
        //填表 s1 = "delete", s2 = "leet"
        //填写dp[0][0]时，s1[0]='d' 和 s2[0]='l' 不相等的话就有两种可能，丢弃 s1[0]或s2[0]，丢弃d,
        //然后填写 dp[0][1]， 即比较 d 和 le 之间的公共子序列 最大 ASCII 和，
        //比较d和e如果e大，那么比较d与e之前的，而dp这个数组之前已经计算过了，不用重复计算，就是dp[0][1-1]
        //如果d大，那么比较 d之前的与e，即dp[0-1][1]
        //就是比较小的之前的
        for(int i = 1; i <= l1; i++){
            for(int j = 1; j <= l2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    //相等
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                //下面的是错误逻辑：当前这一步，选 ASCII 大的字符
                //正确的逻辑是：当前这一步，选 能让后续整体结果最大的路径，而当前两个字符不相等都不能选，没资格成为最大的成员
//                else if(s1.charAt(i-1) > s2.charAt(j-1)){//不相等
//                    dp[i][j] = Math.max(s1.charAt(i-1), dp[i][j-1]);
//                }
//                else{//不相等
//                    dp[i][j] = Math.max(s2.charAt(j-1), dp[i-1][j]);
//                }
            }
        }
        int sum1 = 0; int sum2 = 0;
        for(char c : s1.toCharArray()){sum1 += c;}
        for(char c : s2.toCharArray()){sum2 += c;}
        return sum1 + sum2 - 2 * dp[l1][l2];
    }
}
