package com.leetcode.hot100.array;

import java.util.ArrayList;
import java.util.List;

public class _1390_SumFourDivisors {
    /**
     * 1390 四因数
     * 给你一个整数数组 nums，
     * 请你返回该数组中恰有四个因数的这些整数的各因数之和。如果数组中不存在满足题意的整数，则返回 0 。
     * */
    //暴力求解 超时了
    public int sumFourDivisorsV1(int[] nums) {
        int ans = 0;
        for(int num : nums){
            ans += getSumForeDivisors(num);
        }
        return ans;
    }
    private int getSumForeDivisors(int num) {
        if(num < 6) return 0;
        int ans = 1 + num;
        int cnt = 2;
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                cnt += 2;
                int other = num / i;
                if(other == i){
                    ans += i;
                    cnt--;
                }
                else ans += (i + other);
            }
            if(cnt > 4){
                ans = 0;
                break;
            }
        }
        if(cnt < 4) ans = 0;
        return ans;
    }
    //思路二 四个因子有两种情况
    // ️n = p³，其中 p 是质数
    //n = p × q，其中 p、q 是两个不同的质数
    public int sumFourDivisorsV2(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += helper(num);
        }
        return ans;
    }
    public int helper(int num) {
            if(num < 6) return 0;
            //判断是否为第一种情况：
            for(int i = 2; i * i * i <= num; i++){
                if(i * i * i == num && isPrime(i))
                    return 1 + i + i*i + num;
            }

            //第二种情况
            for(int i = 2; i * i < num; i++){
                if(num % i == 0){
                    int other = num / i;
                    if(isPrime(i) && i != other && isPrime(other))
                        return 1 + i + other + num;
                    //这一步的后续的return，有因子但不满足条件，一定大于4个因子
                    return 0;
                }
            }
        return 0;
    }
    private boolean isPrime(int num){
        if(num < 2) return false;
        if(num < 4) return true;
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
