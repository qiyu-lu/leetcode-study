package com.leetcode.hot100.subString;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _239_MaxSlidingWindow {
    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     *
     单调递减队列的含义：队列中存的是当前窗口内“可能成为最大值”的下标，
     并且从队头到队尾，对应的 nums 值严格递减。

     - 队头元素始终是当前窗口的最大值
     - 如果某个元素左边出现了更大的数，它会在 while 中被淘汰
     - 因此，窗口左边如果不是最大值，它根本不会出现在队列中
     - 窗口右移时：
     * 若队头下标过期（i - k），用 if 删除
     * 新元素进来时，用 while 移除所有比它小或相等的队尾元素
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[n - k + 1];//存储最终结果，预分配空间
        for(int i = 0; i < n; ++i){
            // 1️⃣ 移除已经滑出窗口的下标
            if(!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            // 2️⃣ 维护单调递减（移除比当前值小的）
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            // 4️⃣ 窗口形成后，记录最大值
            if(i >= k - 1) ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
