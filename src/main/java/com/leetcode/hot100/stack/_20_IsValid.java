package com.leetcode.hot100.stack;

import java.util.Stack;

public class _20_IsValid {
    /**
     * 20 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * */
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty())
                stack.push(s.charAt(i));
            else{
                if(stack.peek() == '(' && s.charAt(i) == ')'
                || stack.peek() == '[' && s.charAt(i) == ']'
                || stack.peek() == '{' && s.charAt(i) == '}'){
                    stack.pop();
                }
                else{
                    stack.push(s.charAt(i));
                }
            }
        }
        return stack.isEmpty();
    }

}
