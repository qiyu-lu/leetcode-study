package com.leetcode.hot100.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _84_LargestRectangleArea {
    /**
     * 84 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * */
    //错误的算法：现在是“从最早的柱子开始算”，采用的队列，是先进先出，
    //比如：heights = [2, 1, 5, 6, 2] 走到 2（最后一个）时：
    //队列里可能是 [1, 5, 6].但实际高度为 1 的矩形可以 横跨五个柱子
    public int largestRectangleAreaError(int[] heights) {
        int l = heights.length;
        if(l < 1) return 0;
        Queue<Integer> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        queue.add(heights[0]);
        for(int i = 1; i < l; i++){
            if(heights[i] > queue.peek()){
                queue.add(heights[i]);
            }
            else{//找到了比栈顶元素小的，形成了一个矩形
                while(!queue.isEmpty()){
                    int tempRectangle = queue.peek() * queue.size();
                    max = Math.max(max, tempRectangle);
                    queue.poll();
                }
                queue.add(heights[i]);
            }
        }
        while(!queue.isEmpty()){
            int tempRectangle = queue.peek() * queue.size();
            max = Math.max(max, tempRectangle);
            queue.poll();
        }
        return max;
    }

    //正确的算法的算法是 矩形的宽度取决于“左右第一个更矮的柱子的位置”
    //对每一根柱子，找到：左边第一个比它矮的柱子 右边第一个比它矮的柱子
    //一个矩形的高度 = 区间内的最小高度
    //如果你固定某根柱子 i 为最小高度,那你就只能往左右扩展，直到遇到比它矮的柱子为止

    //使用栈的话，就是当前索引 i 的高度和当前栈中栈顶的比较，如果比栈顶的高，就压入栈，
    //如果比栈顶 cur 对应的元素小，则说明，当前栈顶的这个可以形成一个矩形，
    //这时需要 找到 栈中 栈顶的元素 左边的第一个小于它的元素 索引为left.
    //那么为了方便，就可以等于时不进行压入栈，
    //left+1到cur 一段就是以栈顶 cur 对应的元素为高的矩形
    public int largestRectangleArea(int[] heights) {
        int length = heights.length;
        int max = 0;
        if(length < 1) return max;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < length; i++){
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int curIndex = stack.pop();
                int rightIndex = i;
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();

                int curRectangle = (rightIndex - leftIndex - 1) * heights[curIndex];
                max = Math.max(max, curRectangle);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int curIndex = stack.pop();
            int rightIndex = length;
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int curRectangle = (rightIndex - leftIndex - 1) * heights[curIndex];
            max = Math.max(max, curRectangle);
        }
        return max;
    }
}
