package com.leetcode.hot100.other;

/**
 * 75.颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * */
public class _75_SortColors {
    //直接排序不也是可以的吗

    //冒泡排序
    public void sortColorsBubble(int[] nums) {
        int len = nums.length;
        for(int i = 1; i <= len-1; i++){
            for(int j = 0; j < len-i; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }
    //计数排序，因为种类是有限的，3种，可以先遍历一遍数组，然后得到每种颜色的数量，然后进行写回数组
    public void sortColorsCount(int[] nums) {
        int len = nums.length;
        int cntRed = 0, cntWhite = 0, cntBlue = 0;
        for(int num : nums){
            if(num == 0){
                cntRed++;
            }
            else if(num == 1){
                cntWhite++;
            }
            else if(num == 2){
                cntBlue++;
            }
        }
        for(int i = 0; i < len; i++){
            if(i >= 0 && i < 0 + cntRed) nums[i] = 0;
            else if(i >= cntRed && i < cntRed + cntWhite) nums[i] = 1;
            else if(i >= cntWhite && i < cntRed + cntWhite + cntBlue) nums[i] = 2;
        }
    }

    //三指针,数组分类 + 双指针思维
    //使用三个指针，将数组分为四类，0、1、2的区域，以及当前未处理的区域
    //一个指针指着0的区域，一个指针指着2的区域，一个指针指着未处理的区域
    //left    当前0应该放的位置
    //i       当前遍历的位置
    //right   当前2应该放的位置
    public void sortColorsThreePoints(int[] nums) {
        int len = nums.length;
        int redPoint = 0;
        int bluePoint = len - 1;
        int currPoint = 0;
        while(currPoint <= bluePoint){
            if(nums[currPoint] == 0){
                int temp = nums[redPoint];
                nums[redPoint] = nums[currPoint];
                nums[currPoint] = temp;
                redPoint++;
                currPoint++;
            }
            else if(nums[currPoint] == 2){
                int temp = nums[bluePoint];
                nums[bluePoint] = nums[currPoint];
                nums[currPoint] = temp;
                bluePoint--;
            }
            else if(nums[currPoint] == 1){
                currPoint++;
            }
        }
    }

}
