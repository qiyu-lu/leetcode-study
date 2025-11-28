package com.leetcode.hot100.twoPointers;

public class _283_MoveZeros {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 思路一：把 nums 当作栈，用一个栈记录非零元素。最后，在栈的末尾添加0元素
     * 思路二：把 0 视作空位。我们要把所有非零元素都移到数组左边的空位上，并保证非零元素的顺序不变。
     *          一个指针用来遍历数组，另一个指针用来保证两个指针间的数是0，
     *          保证j 到 i-1 这几个索引上是0,或者两个指针重合
     * */
    public void moveZeroesStack(int[] nums) {
        int length = nums.length;
        int stackSize = 0; //这个指针的对应位置代表着此处可以插入数值，也表示栈的长度
        for(int i = 0; i < length; ++i){
            if(nums[i] != 0){
                nums[stackSize++] = nums[i];
            }
            else{}
        }
        //记录有几个非零数，然后将后面的剩余的变为0
        while(stackSize < length){
            nums[stackSize] = 0;
            stackSize++;
        }
    }
    //这个也是思路一，类似栈的思维
    public void moveZeroesTwoPointers1(int[] nums) {
        int i = 0;
        int j = 0;
        while(i < nums.length){
            if(nums[i] == 0){
                i++; //j 不动，i继续遍历，
            }
            else{
                nums[j++] = nums[i++];
            }
        }
        while(j < nums.length){
            nums[j++] = 0;
        }
    }
    //思路二与思路一的区别是，思路一的话不是交换，而是直接覆盖，那么后面会有非零的数，
    // 后续还需要变为0的一步操作，如果是全0数组，那就需要遍历两遍
    // 思路二交换的话，保证两个指针之间维护着一组0，最终移动到数组尾
    public void moveZeroesTwoPointers2(int[] nums) {
        int i = 0;
        int j = 0;
        while(i < nums.length){
            if(nums[i] == 0){
                i++; //j 不动，i继续遍历，
            }
            else{
                int temp = nums[j];
                nums[j++] = nums[i];
                nums[i++] = temp;
            }
        }
    }
}
