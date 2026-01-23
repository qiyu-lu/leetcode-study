package com.leetcode.hot100.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _763_PartitionLabels {
    /**
     * 763 划分字母区间
     * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
     * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     * 返回一个表示每个字符串片段的长度的列表。
     * */

    //想用贪心算法? 一个一个字母的划到前面的字符串中，要求当前字符串中的字母只有当前字符串独有，
    //遇到一个新的字符，看一下前面的所有字符串中有没有如果有，找到是哪个字符串的，将从该字符串开始到这个新字符的所有字符合并到一个字符串中
    //如果当前的字符前面的所有字符串中都没有，那么这个新的字符串就可以新开一个字符串

    //但是如果这样的话 是站在了“字符串构造”的视角，而不是**“区间覆盖”的视角**，在合并字符串的步骤中这是一步回头的操作，就违背了贪心的原则

    //每个字母的“出现范围”，必须完全落在某一个片段里，这个字符，最晚要出现到哪里
    public List<Integer> partitionLabels(String s) {
        int len = s.length();
        int[] ends = new int [26];//这个数组记录每个字母的最晚结束的位置，然后最多有len个不同的字母
        Arrays.fill(ends, -1);
        for(int i = 0; i < len; i++){
            ends[s.charAt(i) - 'a'] = i;
        }//经过这个循环后，这个数组中的不同位置代表着不同字母的结束的索引

        List<Integer> ans = new ArrayList<>();
        int cnt = 0;
        int lateEnd = 0;//维护当前片段的最晚结束的索引
        for(int i = 0; i < len; i++){
            //当前的字母最晚结束到这个索引
            lateEnd = Math.max(lateEnd, ends[s.charAt(i) - 'a']);
            cnt++;
            if(i == lateEnd){
                ans.add(cnt);
                cnt = 0;
            }
        }
        return ans;
    }
}
