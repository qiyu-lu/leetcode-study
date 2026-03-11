package com.leetcode.hot100.other;

/**
 * 287.寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 提示：
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * */
public class _287_FindDuplicate {

    // 有 n+1 个数，数字范围在 1到n 之间，那么就是还有一个数是 1到n 中的数的重复
    // 那么不重复的 n 个数的和是 (1+n)*n/2 ，求出数组总和，减去这个不重复的数的和就是重复数的和

    //但是有个问题，重复的数不是只重复一次，比如可以这 n+1 个数中都是重复的。
    public int findDuplicateError(int[] nums) {
        int n = nums.length - 1;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        return sum - (1+n)*n/2;
    }

    //上面的是错误的思路

    //这个思路是把数组看成链表，例如nums = [1,3,4,2,2]，索引和值相对应，0-1， 1-3，2-4，3-2，4-2
    //走一遍链表 0-1-3-2-4-2，会出现环，环的入口就是重复数字，这就是Floyd 判圈算法，之前也有个算法使用了这个思路
    //使用快慢指针，如果有环一定会相遇,相遇后两个指针同时每次走一步
    // next = nums[index]  index-> nums[index]

    //证明：p1和p2指针分别速度为一次走1格和一次走2格的，首先如果有环的话一定会相遇，假设总共链表长度为 a+b，b是环的长度
    //假设相遇时在环中的 x 位置相遇，相遇时两者已经走过的环圈数分别为m圈和n圈，
    //p1走过的路程为：a+mb+x; p2走过的路程为: a+nb+x;
    //因为速度是2倍的原因有等式： 2(a+mb+x)=a+nb+x -> a+(2m-n)b+x=0 -> a+(2m-n)b=-x -> a+(2m-n)b+b=b-x
    //b-x是环的后半段,有公式： a+b+(2m-n)b=b-x，
    //根据这个公式可以得到：现在在x位置相遇了，如果此时将其中一个指针放到起点a处，然后两个指针一个从起点出发一个从x出发，速度都是1的话
    //一定会再次相遇，并且相遇的位置就是 b 即环的起点，p1走了a+b后再走了xx圈环到达环的起点，p2走了环的后半段b-x，到达环的起点b
    public int findDuplicateFloyd(int[] nums) {
        int p1 = 0, p2 = 0;
        while ((p1=nums[p1]) != (p2=nums[nums[p2]])) {}
        //此时是相等了
        p1 = 0;
        while((p1=nums[p1]) != (p2=nums[p2])){}
        return p1;
    }

//
    public int findDuplicateBinarySearch(int[] nums) {
        int n = nums.length-1;
        int left = 1;
        int right = n;
        while(left < right){
            int mid = left + (right - left)/2;
            int cnt = 0;
            for(int num : nums){
                if(num <= mid) cnt++;
            }
            if(cnt > mid){
                right = mid;
            }
            else {
                left = mid+1;
            }
        }
        return left;
    }
}
