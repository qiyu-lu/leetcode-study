package com.leetcode.hot100.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_Merge {
    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * */
    public int[][] mergeBf(int[][] intervals) {
        int n =  intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for(int i = 1; i < n; i++){
            if(ans.get(ans.size()-1)[1] >= intervals[i][0]){//上一个的右区间比这个的左区间大，则有重叠
                ans.get(ans.size()-1)[1] = Math.max(ans.get(ans.size()-1)[1], intervals[i][1]);
            }
            else ans.add(intervals[i]);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
