package com.leetcode.hot100.dynamicProgramming;

import java.util.Arrays;

public class _70_ClimbStairs {
    /**
     * 70 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * */

    //假设f(n) 含义是从起点到n阶台阶的方法 因为只有两种爬楼梯的方式，我们看从n-1 n-2到n的走法
    //f(n) = f(n-1) 从n-1阶台阶到n阶台阶只有一种方法 那么从1到n-1的每一种走法f(n-1)都可以走一步到达n阶台阶，

    //f(n) = f(n-2) + f(n-1) 从n-2阶台阶到n阶台阶有两种方法，
    //第一种从n-2爬两个台阶上到n，从1到n-2的每一种方法f(n-2)都可以爬两个台阶到n 有f(n-2)种
    //第二种先爬到n-1再从n-1爬到n，而这个方法已经被包含到  f(n-1) 中了，
    //从n-1阶台阶到n阶台阶只有一种方法 那么从1到n-1的每一种走法f(n-1)都可以走一步到达n阶台阶，所以有 f(n-1)种



    //f(n) = f(n-1)+f(n-2) + f(n-1) 从n-3阶台阶到n阶台阶有三种方法，
    //n-3到n-2到n-1到n， f(n-1)种
    //从n-3到n-2到n  f(n-2)
    //从n-3 到n-1到n  f(n-1)

    public int climbStairs(int n) {
//        return dfs1(n);
        int[] visited = new int[n+1];
        Arrays.fill(visited,-1);
        return dfs2(n, visited);
    }
    //这个递归会有问题，逻辑没有问题，但是复杂度超了，导致会超时。这里面会有重复运算的部分。
    private int dfs1(int n){
        if(n<=1) return 1;
        return dfs1(n-1)+dfs1(n-2);
    }
    private int dfs2(int n, int[] cnt){
        if(n<=1) return 1;
        if(cnt[n] != -1) return cnt[n];
        cnt[n] = dfs2(n-1, cnt) + dfs2(n-2, cnt);
        return cnt[n];
    }

}
