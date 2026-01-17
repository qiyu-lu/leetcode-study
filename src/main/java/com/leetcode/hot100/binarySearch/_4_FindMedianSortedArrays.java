package com.leetcode.hot100.binarySearch;

public class _4_FindMedianSortedArrays {
    /**
     * 4 寻找两个正序数组中的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //数组合并后有 m+n个数，
        int m = nums1.length;
        int n = nums2.length;
        if(m > n){
            return findMedianSortedArrays(nums2,nums1);
            //递归来减少多次判断分支
        }
        //然后可以将中位数等价于：把两个数组分别分为左右两个部分
        //假设A左边取i个，B左边取j个，构成合并数组的左半部分
        //中位数的定义保证了， i+j= (m+n+1)/2
        //第一个核心约束：数量平衡

        //第二个核心约束：顺序正确
        //左边的最大值小于右边的最小值
        //如果找到切分点，则中位数则立刻可以找到了
        //若 m+n 为奇数：median=max(A[i−1],B[j−1])
        //若 m+n 为偶数：median= ( max(A[i−1],B[j−1]) + min(A[i],B[j]) )/2


        //在两个数组中较短的一个中进行查找，减少工作量
        int totalLeft = (m+n+1)/2;
        int left = 0;
        int right = m;
        while(left <= right){
            int i = left + (right - left) / 2;
            int j = totalLeft - i;
            //数组a左边挑选的最大值：
            int nums1LeftMax = i==0 ? Integer.MIN_VALUE : nums1[i-1];
            //数组b左边挑选的最大值
            int nums2LeftMax = j==0 ? Integer.MIN_VALUE : nums2[j-1];
            //数组a右边剩下的最小值
            int nums1RightMin = i==m ? Integer.MAX_VALUE : nums1[i];
            //数组b右边剩下的最小值
            int nums2RightMin = j==n ? Integer.MAX_VALUE : nums2[j];

            //满足情况的，即合并后的符合情况的
            if(nums1LeftMax <= nums2RightMin
               && nums2LeftMax <= nums1RightMin){
                if((m+n) % 2 == 1){
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
                else{
                    return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            }
            //不满足情况的
            else if(nums1LeftMax > nums2RightMin){
                //左边大，就需要减少左边选的数的大小
                //数组1的左半段的最大值大于数组2的右半段的最小值 就需要减少数组1中左半段的个数
                right = i - 1;
            }
            else{
                //数组2左半段的最大值大于数组1右半段的最小值，说明需要减少数组2中左半段的个数，
                //即增加数组1中左半段的个数
                left = i + 1;
            }
        }
        //如同二分查找，一般到这里说明没找到，返回索引的话就是返回-1
        return 0.0;
    }
}
