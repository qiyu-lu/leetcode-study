package com.leetcode.hot100.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class _75_Test {

    private int[] nums  = new int[]{2,0,2,1,1,0};
    private  _75_SortColors test = new _75_SortColors();
    @BeforeEach
    void init(){
        nums = new int[]{2,0,2,1,1,0};
    }

    @DisplayName("测试冒泡排序")
    @Test
    public void sortBubble(){
        System.out.println("原数组: " + Arrays.toString(nums));
        test.sortColorsBubble(nums);
        System.out.println("冒泡排序：" + Arrays.toString(nums));
    }

    @DisplayName("测试计数排序")
    @Test
    public void sortCount(){
        System.out.println("原数组: " + Arrays.toString(nums));
        test.sortColorsCount(nums);
        System.out.println("计数：" + Arrays.toString(nums));
    }

    @DisplayName("三指针排序")
    @Test
    public void sortThreePoints(){
        System.out.println("原数组: " + Arrays.toString(nums));
        test.sortColorsThreePoints(nums);
        System.out.println("三指针：" + Arrays.toString(nums));
    }
}
