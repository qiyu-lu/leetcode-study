package com.leetcode.hot100.binarySearch;

public class _34_SearchRange {
    /**
     * 34 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * */
    //第一个思路是先找到然后遍历前后找起点和终点
    public int[] searchRange(int[] nums, int target) {
        int searchInsertIndex = searchInsert(nums, target);
        if(searchInsertIndex == -1) return new int[]{-1, -1};
        int left = searchInsertIndex;
        int right = searchInsertIndex;
        while(left - 1 >= 0 && nums[left-1] == target){
            left--;
        }
        while(right + 1 < nums.length && nums[right+1] == target){
            right++;
        }
        return new int[]{left, right};
    }

    private int searchInsert(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    //我的第一个思路不符合复杂度的要求，假如全是重复的，就会发生在二分之后又遍历了一次，
    //正确的思路是：找两次对应的位置
    //一次找「第一个 ≥ target 的位置 即左边界
    //一次找「第一个 > target 的位置 即右边界
    public int[] searchRangeV2(int[] nums, int target) {
        int n = nums.length;
        int low = lowerBound(nums, target);
        int up = lowerBound(nums, target + 1) - 1;
        //判断是否出界了
//        if(low > up || (low < 0 || low >= n) || (up < 0 &|| up >= n))
//            return new int[]{-1, -1};
        //上面的判断条件是冗余的
        if (low == n || nums[low] != target)
            return new int[]{-1, -1};

        return new int[]{low, up};
    }
    //定义函数作用为：找到第一个大于等于目标值的索引
    //这里返回的可能会超出合法索引
    private int lowerBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] >= target) right = mid-1;
            else left = mid + 1;
        }
        return left;//这里返回的可能会超出合法索引
    }
}
