package com.leetcode.hot100.binarySearch;

public class _153_FindMin {
    /**
     * 153 寻找旋转排序数组中的最小值
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，
     * 原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * */
    //这道题和33题类似，旋转n次和旋转1次结果是一样的，都会产生一个两段分别升序的数组，
    //那么能知道最小的值就在乱序中的那一段的后半段有序数组的第一个
    //这是第一版的代码，出现的问题是，旋转不一定会出现乱序的情况，假如一个7个数字的数组，旋转7次回到原来的升序形式
    //所以有两种，一种乱序，一种有序
    //下面的代码可以应付无序的情况，而无法处理有序的情况
    public int findMinV1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(right - left > 1){
            int mid = left + (right - left)/2;
            if(nums[left] < nums[mid]){
                //左半段是有序的,旋转点在后半段
                left = mid;//必须包含mid，假如mid+1就是最小值就会导致找不到
            }
            else {
                //左半段出现无序,从 mid 到 right 右半段是有序的
                right = mid;
            }
        }
        return nums[right];
    }
    public int findMinV2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(right - left > 1){
            int mid = left + (right - left)/2;
            if(nums[left] < nums[mid]){
                //左半段是有序的,旋转点在后半段
                left = mid;//必须包含mid，假如mid+1就是最小值就会导致找不到
            }
            else {
                //左半段出现无序,从 mid 到 right 右半段是有序的
                right = mid;
            }
        }
        if(nums[left] < nums[right]) return nums[0];
        else return nums[right];
    }

    //上面的思路是属于一种打补丁的形式，正确的思路是：mid 属于「左大段」还是「右小段」
    public int findMinV3(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[right]){
                //中间点大于最右面的点，那么此时数组一定不是有序数组，
                //最小值一定在 mid + 1 到 right 这一段
                left = mid + 1;
            }
            else{
                //nums[mid] <= nums[right]
                //此时，有两种可能，一种是无序数组，最小值在 left 到 mid这一段
                //有序数组，最小值也在left 到 mid这一段
                right = mid;
            }
            //终止条件，两个数是 left = mid， 如果mid > right，说明 left = mid+1就是最小值
            //如果mid <= right， 此时left就是最小值
        }
        return nums[left];
    }
}
