package com.leetcode.hot100.greedy;

public class _55_CanJump {
    /**
     * 55 跳跃游戏
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     * */
    //我的一开始的思路是：从后向前数 n 个数的数组
    //查看n-2索引位置中的数是否大于1，不是的话看n-3位置的数是否大于2 ...

    //但是上面的思路不好实现，正确的思路是 “到目前为止，我最远能到哪？” 是否存在一条路径能让我覆盖到这里
    //不需要知道具体怎么跳，只关心：当前i这个位置 是不是在可达范围内，如果是，它能不能扩大我的可达范围
    public boolean canJumpV1(int[] nums) {
        //维护一个最远可达位置
        int maxReach = nums[0];
        int length = nums.length;
        for(int i = 1; i < length; i++){
            if(i > maxReach){
                //说明从起点出发到不了i这个位置，那么也就到不了后面的位置，直接返回
                return false;
            }
            //反之，可以到了i这个位置，那么考虑i这个位置能不能给 maxReach 提供贡献
            //从起点出发最远能到达 maxReach 索引这个位置，然后
//            if(nums[i] > (maxReach - i)){
//                maxReach = i + nums[i];
//            }
            //这里i位置的可以进行考虑 当前位置 i 能不能让我走得更远
            if(i + nums[i] > maxReach){
                maxReach = i + nums[i];
            }
            if(maxReach >= length - 1) return true;
        }
        return true;
    }

    //我最开始的思路是一个反向贪心，有没有某个位置 i，能一步跳到当前目标位置
    //先把终点 n-1 索引当作一个目标点，从右向左扫描数组，如果从i位置出发可以到达 当前目标 n-1，
    //那么就说明 如果从某个位置可以到达 i 位置 那么也就说明可以到达终点，那么此时就可以更新目标为 i
    public boolean canJumpV2(int[] nums) {
        int length = nums.length;
        int target = length - 1;

        for(int i = length - 2; i >= 0; i--){
            if(i + nums[i] >= target){
                target = i;
            }
        }
        return target == 0;
    }

}
