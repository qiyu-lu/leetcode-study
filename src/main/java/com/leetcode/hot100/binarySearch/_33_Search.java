package com.leetcode.hot100.binarySearch;

public class _33_Search {
    /**
     * 33 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * */
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int i = 0;
        while(i+1 < nums.length && nums[i] < nums[i+1]){
            i++;
        }
        if(target >= nums[0]){
            return binarySearch(nums, target, 0, i);
        }
        return binarySearch(nums, target, i+1, nums.length-1);
    }
    private int binarySearch(int[] nums, int target, int low, int up){
        if(low > up || low >= nums.length || up >= nums.length) return -1;
        while(low <= up){
            int mid = low + (up - low) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) low = mid + 1;
            else up = mid - 1;
        }
        return -1;
    }
    //我的代码逻辑合格但是复杂度不合格，在遍历找到这个旋转点时复杂度会增加，导致耗时
    //旋转点本身就可以用二分找，甚至可以 根本不显式找旋转点,主要考察在“局部有序”的数组中，动态判断哪一半有序
    //旋转就是将数组切了一刀，将后一段放到数组前面，
    //旋转的话，假设在原数组的p位置进行旋转，那么在数组中只有 p 这里会出现 nums[p] > nums[p+1] 的情况，p之前的是升序的， p 之后的也是升序的，
    //所以在p这里会出现乱序，那么另一段一定会是有序的
    public int searchV2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]){//左半段有序
                if(nums[left] <= target && target < nums[mid]){//在有序的一段
                    right = mid - 1;
                }
                else {//在无序的一段
                    left = mid + 1;
                }
            }
            else{//右半段有序
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
