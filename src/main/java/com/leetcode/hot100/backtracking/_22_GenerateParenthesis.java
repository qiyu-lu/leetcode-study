package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _22_GenerateParenthesis {
    /**
     * 22 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * */
    /**
     *                   []
     *             /            \
     *            (              )
     *        /     \         /     \
     *       (     )       (         )
     *     /  \   /  \    / \      /   \
     *     (   )  (  )   (  )     (    )
     * */
    public List<String> generateParenthesis(int n) {
        dfs(n, 0, 0);
        return res;
    }
    private List<String> res = new ArrayList<>();
    private StringBuilder path = new StringBuilder();
    private String[] parentheses = {"(", ")"};
    //核心：在任意时刻：右括号的数量，不能超过左括号的数量
    private void dfs(int n, int left, int right){
        if(path.length() == 2 * n){
            res.add(path.toString());
            return;
        }
        if(left < n) {
            path.append(parentheses[0]);
            dfs(n, left + 1, right);
            path.deleteCharAt(path.length()-1);
        }
        if(right < left){
            path.append(parentheses[1]);
            dfs(n, left, right + 1);
            path.deleteCharAt(path.length()-1);
        }
    }

    //判断括号的字符串是否有效，原来的想法是先找到所有的可能，然后找到合法的，这样效率太低，应该在递归时就进行判断合法性
    //这个函数是没有用的。
    private boolean isLegal(String str){
        if(str.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++){
            if(!stack.isEmpty() && stack.peek() == '(' && str.charAt(i) == ')'){
                stack.pop();
                continue;
            }
            else {
                stack.push(str.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
