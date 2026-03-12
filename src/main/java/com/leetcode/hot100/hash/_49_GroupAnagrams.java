package com.leetcode.hot100.hash;

import java.util.*;
import java.util.function.Function;

public class _49_GroupAnagrams {
    /**
     * HashSet（只存 key，无 value） 常用方法：
     * set.add(x);
     * set.contains(x);
     * set.remove(x);
     * */
    /**
     * 思路是：对输入的字符串数组中每个字符串转化为字符数组进行排序，
     * 排序后检测这个排序后的字符数组是否在哈希表中，如果不在说明这个字符串和之前遍历的字符串不是字母异位词
     *
     * 如果在哈希表中，那么说明是字母异位词
     * */
    public List<List<String>> groupAnagramsHash(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if(map.containsKey(key)){
                map.get(key).add(s);
            }
            else{
                map.put(key, new ArrayList<>(Arrays.asList(s)));
            }
        }
        return new ArrayList<>(map.values());
    }
    public List<List<String>> groupAnagramsNew(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            map.computeIfAbsent(new String(arr), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
