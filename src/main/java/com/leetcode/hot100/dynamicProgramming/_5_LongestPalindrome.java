package com.leetcode.hot100.dynamicProgramming;

public class _5_LongestPalindrome {
    /**
     * 5.最长回文子串
     * 给你一个字符串 s，找到 s 中最长的 回文 子串。
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     * */
    //第一个思路，从中心进行枚举两边
    public String longestPalindromeV1(String s) {
        //对某个中心，有左右两个指针，
        int len = s.length();
        if(len < 2) return s;

        //这是用来标记哪个是最长的，
        String ans = "";

        for(int i = 0; i < len; i++){
            //当前位置作为中心位置，如果当前要判断的回文串是奇数则中心是一个字符，反之中心是两个字符
            //如果把当前位置当作中心，两边同时向外扩展的话，这样就会只检查奇数长度的回文串，会漏掉偶数长度的回文串。
            //正确的应该是遍历全部的中心，包括奇数中心和偶数中心

            //当前位置是奇数中心
            int maxLen1 = getMaxLenJi(s, i);
            int maxLen2 = 0;
            if(i+1 < len && s.charAt(i) == s.charAt(i+1)){
                maxLen2 = getMaxLenOu(s, i, i+1);
            }
            if(maxLen1 > maxLen2){
                if(maxLen1 > ans.length()){
                    //奇数的最长
                    ans = s.substring(i-maxLen1/2, i+maxLen1/2+1);
                }
                else{}
            }
            else{
                if(maxLen2 > ans.length()){
                    //偶数的最长
                    ans = s.substring(i-(maxLen2/2-1), i+1+(maxLen2/2-1)+1);
                }
            }
        }
        return ans;
    }
    //当前索引位置作为奇数回文串的中心
    private int getMaxLenJi(String s, int i){
        int l = i - 1;
        int r = i + 1;
        //当前不满足奇数串的情况，那么最长回文串就是1
        while(l >= 0 && r < s.length()){
            if(s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            else{
                return r - l - 1;
            }
        }
        return r - l - 1;
    }
    //当前i, j 是偶数回文串的中心
    private int getMaxLenOu(String s, int i, int j){
        int l = i - 1;
        int r = j + 1;
        while(l >= 0 && r < s.length()){
            if(s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            else{
                return r - l - 1;
            }
        }
        return r - l - 1;
    }

    //这一版不反悔局部回文串的长度，而是直接返回回文串
    public String longestPalindromeV2(String s) {
        //对某个中心，有左右两个指针，
        int len = s.length();
        if(len < 2) return s;

        //这是用来标记哪个是最长的，
        String ans = "";

        for(int i = 0; i < len; i++){
            //当前位置作为中心位置，如果当前要判断的回文串是奇数则中心是一个字符，反之中心是两个字符
            //如果把当前位置当作中心，两边同时向外扩展的话，这样就会只检查奇数长度的回文串，会漏掉偶数长度的回文串。
            //正确的应该是遍历全部的中心，包括奇数中心和偶数中心

            //当前位置是奇数中心
            String jiStr = getMaxLenJiv2(s, i);
            String ouStr = "";
            if(i+1 < len && s.charAt(i) == s.charAt(i+1)){
                ouStr = getMaxLenOuv2(s, i, i+1);
            }
            if(jiStr.length() > ouStr.length()){
                if(jiStr.length() > ans.length()){
                    //奇数的最长
                    ans = jiStr;
                }
                else{}
            }
            else{
                if(ouStr.length() > ans.length()){
                    //偶数的最长
                    ans = ouStr;
                }
            }
        }
        return ans;
    }
    //当前索引位置作为奇数回文串的中心
    private String getMaxLenJiv2(String s, int i){
        int l = i - 1;
        int r = i + 1;
        //当前不满足奇数串的情况，那么最长回文串就是1
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1, r);
    }
    //当前i, j 是偶数回文串的中心
    private String getMaxLenOuv2(String s, int i, int j){
        int l = i - 1;
        int r = j + 1;
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1, r);
    }

    //这一版对于奇数和偶数不分开判断
    public String longestPalindromeV3(String s) {
        //对某个中心，有左右两个指针，
        int len = s.length();
        if(len < 2) return s;

        //这是用来标记哪个是最长的，
        String ans = "";

        for(int i = 0; i < len; i++){
            //当前位置作为中心位置，如果当前要判断的回文串是奇数则中心是一个字符，反之中心是两个字符
            //如果把当前位置当作中心，两边同时向外扩展的话，这样就会只检查奇数长度的回文串，会漏掉偶数长度的回文串。
            //正确的应该是遍历全部的中心，包括奇数中心和偶数中心

            String tempStr = getMaxLenV3(s, i);
            if(tempStr.length() > ans.length()){
                ans = tempStr;
            }
        }
        return ans;
    }
    //不分奇数和偶数的两个函数，而是在函数内部进行判断
    private String getMaxLenV3(String s, int i){
        int l = i;
        int r = i;
        //当前不满足奇数串的情况，那么最长回文串就是1
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        String jiStr = s.substring(l+1, r);
        //进行偶数的判断
        l = i;
        r = i + 1;
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        String ouStr = s.substring(l+1, r);

        return jiStr.length() > ouStr.length() ? jiStr : ouStr;
    }

    //dp思路的解法
    //dp[i][j] 表示 字符串中索引 i...j 这段是否是回文串，就是判断 s[i] 和 s[j] 是否相等，如果相等继续判断
    //判断 dp[i+1][j-1]是否是回文串，继续这样的逻辑，
    //边界是当 j-i <= 1 时此时的子串只有1个或者2个字符，判断 s[i] 和 s[j] 是否相等
    public String longestPalindromeDp(String s) {
        int n = s.length();
        if(n < 2) return s;

        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;//最短的长度就是一个字母
        int startIndex = 0;
        //因为dp[i][j] 的判断有一部分需要依赖 dp[i+1][j-1]，那么就是需要根据短的子串判断长的子串，
        //那么就需要先计算出来短子串的dp
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len - 1 < n; i++){
                if(s.charAt(i + len - 1) == s.charAt(i)){
                    if(len - 1 <= 1){
                        dp[i][i+len-1] = true;
                        if(len > maxLen){
                            maxLen = len;
                            startIndex = i;
                        }
                    }
                    else if(i+1 < n && i+len-1-1 < n && dp[i+1][i+len-1-1]){
                        dp[i][i+len-1] = true;
                        if(len > maxLen){
                            maxLen = len;
                            startIndex = i;
                        }
                    }
                    else{
                        dp[i][i+len-1] = false;
                    }
                }
                else{
                    dp[i][i+len-1] = false;
                }
            }
        }
        return s.substring(startIndex, startIndex+maxLen);
    }

    public String longestPalindromeDpV2(String s) {
        int n = s.length();
        if(n < 2) return s;

        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;//最短的长度就是一个字母
        int startIndex = 0;
        //因为dp[i][j] 的判断有一部分需要依赖 dp[i+1][j-1]，那么就是需要根据短的子串判断长的子串，
        //那么就需要先计算出来短子串的dp
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len - 1 < n; i++){
                if(s.charAt(i + len - 1) != s.charAt(i)){
                    dp[i][i+len-1] = false;
                }
                else{
                    if(len - 1 <= 1){
                        dp[i][i+len-1] = true;
                    }
                    else if(i+1<n && i+len-1-1 < n && dp[i+1][i+len-1-1]){
                        dp[i][i+len-1] = true;
                    }
                    else{
                        dp[i][i+len-1] = false;
                    }
                }
                if(dp[i][i+len-1] && len > maxLen){
                    maxLen = len;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex+maxLen);
    }
}
