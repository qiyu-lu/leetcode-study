package com.leetcode.hot100.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _118_Generate {
    /**
     * 118 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1) return res;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        res.add(cur);
        //第一行
        if(numRows == 1) return res;
        for(int i = 1; i < numRows; i++){
            List<Integer> next = getNext(cur);//
            cur = next;
            res.add(cur);
        }
        return res;
    }
    private List<Integer> getNext(List<Integer> cur){
        //知道当前行cur来推断下一行
        List<Integer> next = new ArrayList<>(cur.size()+1);
        next.add(1);
        for(int i = 0; i < cur.size()-1; i++){
            next.add(cur.get(i) + cur.get(i + 1));
        }
        next.add(1);
        return next;
    }
}
