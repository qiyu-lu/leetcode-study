package com.leetcode.hot100.heap;

import java.util.PriorityQueue;

public class _215_FindKthLargest {
    /**
     * 215 数组中的第k个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * */
    //只关心 一个“阈值”元素,而不是整个顺序
    //第 k 大 = 有 k−1 个数比它大，其余都不比它大,只要保证这一点即可
    //现在从左到右扫描数组，随时知道“当前见过的数中，最大的 k 个是谁”
    //只保留 k 个数,并且它们始终是“目前最大的 k 个”,那么就需要这样的数据结构：
    //只保留k个数，并且他们始终是目前最大的k个，
    //有新数进来时，如果它比这k个数里最小的还小，就丢掉这个数
    //如果比最小的还大，那么就把最小的去掉，把新数放入
    //总结一下这个数据结构需要：动态插入元素，动态删除元素，能快速知道当前的最小值

    //因为要剔除的是最小值，也就是k个中的最小值，也就是目前的第k大的数，那么就需要小顶堆（Min Heap）
    //堆顶 = 最小值
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums){
            if(pq.size() < k){
                pq.offer(num);
            }
            else if(num > pq.peek()){
                pq.poll();
                pq.offer(num);
            }
            //其他的情况，不会入堆中
        }
        return pq.peek();
    }
}
