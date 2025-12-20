package com.leetcode.hot100.array;

import java.util.Arrays;
import java.util.HashSet;

public class _41_FirstMissingPositive {
    /**
     * 缺失的第一个正数：
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     * 示例 1：
     * 输入：nums = [1,2,0]
     * 输出：3
     * 解释：范围 [1,2] 中的数字都在数组中。
     * 示例 2：
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 解释：1 在数组中，但 2 没有。
     * 示例 3：
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     * 解释：最小的正数 1 没有出现。
     * */

    //要找到缺失的第1个正数，输入数组中个数为n
    //则这个缺失的正数必定是在这个闭区间中 [1,n+1]
    //如果输入的数组中都是正数且连续，则缺失的正数就是 n+1
    //否则，如果出现了负数，或者正数不连续，则缺失的正数就在[1,n]中
    //那么在输入数组中 元素大于 n 的部分 不会影响到这个缺失的正数的判断
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //下面的代码是我最初的思路，但是由于输入数组长度和元素大小的限制肯定不对
//        int flag = 0;
//        for(int num : nums){
//            if(num <= 0) continue;
//            flag = flag | (1 << (num-1));
//        }

        //上面的思路就是使用一个另类的哈希表
        boolean[] flag = new boolean[n];
        for(int num : nums){
            if(num <= 0 || num > n) continue;
            flag[num-1] = true;
        }
        for(int i = 0; i < n; i++){
            if(!flag[i]) return i+1;
        }
        return n+1;
    }
    //这个遇到重复元素会死循环
    //现在要不使用额外的空间，那么就需要使用原来的数组作为哈希表
    public int firstMissingPositiveWrong(int[] nums) {
        int n = nums.length;
        //使用原本的数组作为哈希表来判断元素是否存在
        //使用索引来进行标志，
        //i 位置 要放 值为 i+1的内容，如果不是的话就说明缺值了，将 i+1返回
        //比如nums[0] 如果不缺值的话要放 1，
        for(int i = 0; i < n; i++){
            if(nums[i] > n || nums[i] <= 0) continue;
            if(nums[i] == i + 1)//满足条件或者说是由于之前处理过的
                continue;
            //经过第一步的判断，说明当前的元素是在1到n 之间的,
            //nums[i]当前元素 和 当前位置 i 不对应，将元素移动到它该在的位置
            // 1 要在索引为0的位置，2要在索引为1的位置，
            //nums[i] 要在索引为 nums[i] - 1 的位置
            int temp = nums[nums[i]-1];
            nums[nums[i]-1] = nums[i];
            nums[i] = temp;//现在 nums[i] 回到正确的位置了，
            i--;//这里是为了重新检查一遍 i 这个位置的数，i减一加一不变
        }
        for(int i = 0; i < n; i++){
            if(nums[i] != i+1) return i+1;
        }
        return n+1;
    }

    public int firstMissingPositiveRight(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] > n || nums[i] <= 0) continue;
            if(nums[i] == i + 1)
                continue;
            //死循环是因为这里，如果 nums[nums[i]-1] 和 nums[i] 相等
            //也执行交换没问题，但是这里由于还需要 i加一减一 原地判断一次，
            //数没发生变化，就一直交换，造成死循环
            if(nums[i] == nums[nums[i]-1]) continue;
            int temp = nums[nums[i]-1];
            nums[nums[i]-1] = nums[i];
            nums[i] = temp;//现在 nums[i] 回到正确的位置了，
            i--;//这里是为了重新检查一遍 i 这个位置的数，i减一加一不变
        }
        for(int i = 0; i < n; i++){
            if(nums[i] != i+1) return i+1;
        }
        return n+1;
    }
}
