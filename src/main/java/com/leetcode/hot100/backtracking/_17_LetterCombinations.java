package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _17_LetterCombinations {
    /**
     * 17 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，
     * 返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 2-abc,3-def,4-ghi,5-jkl,6-mno,7-pqrs,8-tuv,9-wxyz
     * */
    //"23"
    //                        abc
    //                   /     |      \
    //                  a      b       c
    //               wxyz     wxyz      wxyz
    //        /  |   |  \    /| | \  / |  |  \
    //      w   x    y   z  w x y  z w x  y  z
    //     aw ax  ay    az bw bx by bz cw cx cy cz

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        dfs(digits, 0);
        return res;
    }
    private String[] letters = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    private List<String> res = new ArrayList<>();
    private StringBuilder path = new StringBuilder();

    private void dfs(String digits, int index){
        if(index == digits.length()){
            res.add(path.toString());
            return;
        }
        String curLetters = letters[digits.charAt(index) - '0'];
        for(int i = 0; i < curLetters.length(); i++){
            path.append(curLetters.charAt(i));
            dfs(digits, index+1);
            path.deleteCharAt(path.length()-1);
        }
    }
}
