package com.leetcode.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class _394_DecodeString {
    /**
     * 394 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * 测试用例保证输出的长度不会超过 10^5。
     * */
    //递归版本：
    private int index = 0;
    public String decodeStringV1(String s) {
        StringBuilder res = new StringBuilder();
        int num = 0;
        while(index < s.length()){
            char c = s.charAt(index);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
                index++;
            }
            else{
                if(c == '['){
                    index++;
                    String inner = decodeStringV1(s);
                    for(int i = 0; i < num; i++){
                        res.append(inner);
                    }
                    num = 0;//重置重复次数
                }
                else if(c == ']'){
                    index++;
                    break;
                }
                else{
                    res.append(c);
                    index++;
                }
            }
        }
        return res.toString();
    }

    //使用栈来进行模拟 把栈想成存 “递归现场”：(上一次的字符串, 上一次的重复次数)
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }
            else if(c == '['){
                numStack.push(num);
                strStack.push(cur);//先记录现场
                num = 0;
                cur = new StringBuilder();
            }
            else if(c == ']'){
                int curNum = numStack.pop();
                StringBuilder prev = strStack.pop();
                for(int j = 0; j < curNum; j++){
                    prev.append(cur);
                }
                cur = prev;
            }
            else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
