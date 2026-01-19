package com.leetcode.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class _739_DailyTemperatures {
    /**
     * 739 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 示例
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * */
    //第一个思路，暴力遍历，测试的三个例子没有问题，提交会超时
    public int[] dailyTemperaturesBF(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(temperatures[j] > temperatures[i]){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
    //优化：有没有办法在“往右找”的过程中，把之前已经比较过的信息复用起来
    //假设在从左到右遍历温度数组：当走到某一天 i
    //你看到一个新温度 temperatures[i],这个温度，能不能帮之前的某些天“找到答案”
    //所以需要知道 那“哪些之前的天”还没被解决，需要一个容器来存放还没找到更高温度的那些天，因为计算的是日期，所以这个容器中放的是索引
    //那么什么容器，这个容器要满足这个条件，存放温度大于当前天的之前的几天，一旦遇到更高温度，就不断解决之前比它低的，那么这就是一个单调栈
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] res = new int[n];

        for(int i = 0; i < n; i++){
            if(stack.isEmpty()){
                stack.push(i);
            }
            else if(temperatures[i] <= temperatures[stack.peek()]){
                stack.push(i);
            }
            else {
                //到了这里，栈里放的都是从栈底到栈顶递减的，今天的温度比从栈底到栈顶的温度都高，
                while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                    int cur = stack.pop();
                    res[cur] = i - cur;
                }
                stack.push(i);
            }
        }
        return res;
    }
    //将循环进行优化
    public int[] dailyTemperaturesV2(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] res = new int[n];

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int cur = stack.pop();
                res[cur] = i - cur;
            }
            stack.push(i);
        }
        return res;
    }
}
