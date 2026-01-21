package com.leetcode.hot100.greedy;

public class _45_Jump {
    /**
     * 45 跳跃游戏 II
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
     * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
     * 0 <= j <= nums[i] 且
     * i + j < n
     * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
     * */
    //先叙述一下跳跃游戏的基础版的正向贪心是，维护一个最远可达位置，如果最远可达位置到了终点就说明可以跳到终点

    //每“跳一步”，应该跳到哪一段区间，才能让下一步的可达范围最大 一次跳跃 = 覆盖一个区间
    //维护三个概念：
    //当前这一步，所能覆盖的最远区间边界，只要还在这个边界内，我都算是“当前这一步能到达的位置
    //在当前区间内，下一步能扩展到的最远位置，我在“本步覆盖的所有点”中，挑一个最优落点，使得下一步最远
    //跳跃次数
    public int jump(int[] nums) {
        int currentEnd = 0;
        int nextMaxReach = 0;
        int steps = 0;

        //不需要处理最后一个位置 当你已经“覆盖”到 n - 1
        //跳跃次数已经在前一次 steps++ 时计算完成了
        //终点只是一个“被覆盖的点”，不是一个“需要再起跳的点”
        for(int i = 0; i < nums.length - 1; i++){
            //在当前区间内，尝试找到更新下一跳最远可达的位置
            nextMaxReach = Math.max(nextMaxReach, i + nums[i]);//0索引的位置最远可以跳到 0+nums[0]

            //当到达当前区间的有边界，那么必须要进行跳
            if(i == currentEnd){
                steps++;
                currentEnd = nextMaxReach;
            }
        }
        return steps;
    }
}
