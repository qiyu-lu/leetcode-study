package com.leetcode.hot100.stack;


import java.util.ArrayDeque;
import java.util.Deque;

public class _155_MinStack {
    /**
     * 155 最小栈
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * 实现 MinStack 类:
     * MinStack() 初始化堆栈对象。
     * void push(int val) 将元素val推入堆栈。
     * void pop() 删除堆栈顶部的元素。
     * int top() 获取堆栈顶部的元素。
     * int getMin() 获取堆栈中的最小元素。
     * */

    //我的想法是使用一个双端队列，队列头放最小值，如果压入的值是比当前栈头尾大或者小的，直接插入即可，
    //在中间的数的话需要进行插入中间 使用用一个栈来进行插入，但是感觉这种的复杂度会有点高
    //这样是维护一个有序的结构，是不对的，

    //正确的思路是在每一次 push 时，把“到当前为止的最小值”一起保存下来，只维护一个最小的状态
    //因为没有弹出最小值的需求
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public _155_MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty()){
            minStack.push(val);
        }//如果存储最小值的栈为空，即当前两个栈都为空，那么当前的最小值就是当前数
        else if(val <= minStack.peek()){
            minStack.push(val);
        }//如果最小值栈不为空，当前值小于等于最小栈的栈顶，说明本数是当前数中最小的，
        //不是最小的就不用存了
    }

    public void pop() {
        if(!stack.isEmpty()){
            int num = stack.pop();
            if(num == minStack.peek()){
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

//改版，使用一个栈
class MinStack {
    Deque<int[]> stack = new ArrayDeque<>();
    public MinStack() {
        stack.push(new int[]{0, Integer.MAX_VALUE});
    }

    public void push(int val) {
        stack.push(new int[]{val, Math.min(this.getMin(), val)});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}