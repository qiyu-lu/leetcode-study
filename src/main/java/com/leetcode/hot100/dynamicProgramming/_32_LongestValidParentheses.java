package com.leetcode.hot100.dynamicProgramming;

public class _32_LongestValidParentheses {
    /**
     * 32 最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
     * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
     * */

    //dp[i] 是 s第i个位置的字符向前数的 最长有效括号子串的长度

//    public int longestValidParenthesesError(String s) {
//        int length = s.length();
//        int[] dp  = new int[length];
//
//        //如果 dp[i-1] 即以 s[i-1] 为结尾的 最长有小括号子串的长度 是m 不为0
//        //那么 dp[i] 的判断需要看 s[i-1-m] 即上一个最长有效字串前一个字符和当前 s[i] 是否对应，如果不对应，那么当前的最长有效字串就是0
//        //如果对应 就是 m+2
//
//        //但是如果 m = 0 的话就不对了，上一个有效的为0，这一个就需要考虑的较多了，需要找到离当前字符最近的一个dp元素不为0的，
//        //然后这个有效的字串左边和右边 如果能对应上，那就是有效的字串长度加上两边的，
//        //如果对不上，那么就从这个有效的子串 右边开始找有效的字串
//
//        for(int i = 1; i < length; i++){
//            int frontIndex = i - 1 - dp[i-1];
//            if(frontIndex >= 0 && dp[i-1] > 0){
//                if(s.charAt(frontIndex) == '(' && s.charAt(i) == ')'){
//                    dp[i] = dp[i-1] + 2;
//                }
//                else
//                    dp[i] = 0;
//            }
//            else if(frontIndex < 0){
//                dp[i] = 0;
//            }
//            else{
//                //这时就是 dp[i-1] == 0 的情况
//
//            }
//        }
//    }

    //上面的是错误的思想，有点不正确
    public int longestValidParentheses(String s) {
        int length = s.length();
        int[] dp = new int[length];
        int ans = 0;
        for(int i = 1; i < length; i++){
            //"...))" 结尾
            if(s.charAt(i - 1) == ')' && s.charAt(i) == ')'){
                int frontIndex = i - 1 - dp[i - 1]; //上一个最长字串之前的索引
                if(frontIndex >= 0 && s.charAt(frontIndex) == '('){ //前一个有效子串之前和当前字符能对应上
//                    dp[i] = frontIndex > 0 ?
//                            dp[i - 1] + 2 + dp[frontIndex - 1] : dp[i - 1] + 2;//这里还有加上前面的有效子串才是最长的
                    dp[i] = dp[i - 1] + 2
                            + (frontIndex - 1 >= 0 ? dp[frontIndex - 1] : 0);//这种写法更为易读

                }
                //如果前一个有效子串之前的字符和当前字符对应不上，就默认为0
            }
            else if(s.charAt(i - 1) == '(' && s.charAt(i) == ')'){
                //这种情况dp[i-1]肯定为0 ，因为 以s.charAt(i - 1) == '(' 为结尾的最长有效子串长度就是0，但是还要考虑前前的那个
                dp[i] = i >= 2 ? dp[i-2] + 2 : 2;
            }
            //这种情况就是 以 ".... )("
            else if(s.charAt(i - 1) == ')' && s.charAt(i) == '('){

            }
            //这种是 以 "....((" 为结尾的，不用进行任何操作就是为0
            else {

            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
