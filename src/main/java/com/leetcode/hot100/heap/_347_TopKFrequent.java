package com.leetcode.hot100.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347_TopKFrequent {
    /**
     * 347 前k个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，
     * 请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //这里不使用 默认的构造器，不然堆中是按照数的大小进行排序的
//        PriorityQueue<Integer> heap = new PriorityQueue<>(
//                (a, b) -> map.get(a) - map.get(b)
//        );
//
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            if(heap.size() < k){
//                heap.add(entry.getKey());
//            }
//            else if(entry.getValue() > map.get(heap.peek())){
//                heap.poll();
//                heap.add(entry.getKey());
//            }
//        }
//        int[] res = new int[k];
//        int idx= 0;
//        while(!heap.isEmpty()){
//            res[idx++] = heap.poll();
//        }
//        return res;


        //上面的是自定义比较器，现在是堆里存储一对数
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(heap.size() < k){
                heap.add(new int[]{entry.getKey(), entry.getValue()});
            }
            else if(entry.getValue() > heap.peek()[1]){
                heap.poll();
                heap.add(new int[]{entry.getKey(), entry.getValue()});
            }
        }
        int[] res = new int[k];
        int idx= 0;
        while(!heap.isEmpty()){
            res[idx++] = heap.poll()[0];
        }
        return res;
    }
}
