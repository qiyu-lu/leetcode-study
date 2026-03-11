package com.leetcode.hot100.other;

/**
 * 31.下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * */


import java.util.Arrays;

import static java.util.Collections.reverse;

/**
 * 字典序其实就是 像字典一样比较大小。
 * 比如比较两个数组：
 * 比较规则：
 * 先比较第一个元素
 * 如果一样，再比较第二个
 * 继续往后
 *
 * 对于 [1,2,3]，所有排列按字典序排列就是：
 * 1. [1,2,3]
 * 2. [1,3,2]
 * 3. [2,1,3]
 * 4. [2,3,1]
 * 5. [3,1,2]
 * 6. [3,2,1]
 * */
public class _31_NextPermutation {

    //在右边找一个地方，让排列“刚好变大一点点”
    //例如:当前：1 3 5 4 2      下一个：1 4 2 3 5
    // 会发现，当前的 5 4 2 是一个递减的，然后它的下一个就是 尽量保持左边不变，只调整右边
    //从右边开始找可以增加的位置
    public void nextPermutationV1(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        for(; i >= 0; i--){
            if(nums[i] < nums[i+1])
                break;
        }
        //此时的  i 索引的位置比后面索引  i+1 位置的数小
        //那么此时的下一个排列就是将i 位置的数变为 后面序列中较小的数，然后将后面的数进行排列为递增。
        if(i < 0) {
            //说明整个数组是递减的
            Arrays.sort(nums);
        }
        else{
            //需要从后面的数组中找到刚刚比i位置索引大的数
            int minIndex = i + 1;
            for(int j = i + 1; j < len; j++){
                if(nums[j] > nums[i] && nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
            Arrays.sort(nums, i+1, len);
        }
    }

    //找到minIndex后交换后不需要排序，直接反转即可
    //minIndex的数刚刚比 i 索引的数大， minIndex左边的更大，右边的比i的小，因此交换后 i+1 到len也是有序的。
    //因为原本从 i+1 到 len 是降序的，找minIndex可以直接从右向左找，第一个大于i索引位置的数就是正确的数
    public void nextPermutationV2(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        for(; i >= 0; i--){
            if(nums[i] < nums[i+1])
                break;
        }
        if(i<0){
            reverse(nums, 0, len-1);
        }
        else{
            for(int j = len - 1; j > i; j--){
                if(nums[j] > nums[i]){
                    swap(nums, i, j);
                    break;
                }
            }
            reverse(nums, i+1, len-1);
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int left, int right){
        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
