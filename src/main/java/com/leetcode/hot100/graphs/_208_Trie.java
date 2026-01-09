package com.leetcode.hot100.graphs;

public class _208_Trie {
    /**
     * 208 实现前缀树 Trie
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
     * 用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
     * 请你实现 Trie 类：
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
     * 否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，
     * 返回 true ；否则，返回 false 。
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     * */
    //每个节点代表“一个字符” 从根到某个节点，路径上的字符连起来，就是一个前缀
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        TrieNode(){
            this.isEnd = false;
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public _208_Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for(char chr : word.toCharArray()){
            int index = chr - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
        //表示这个位置是插入的字符串的结尾，用于startsWith函数使用
    }

    public boolean search(String word) {
        TrieNode node = root;
        for(char chr : word.toCharArray()){
            int index = chr - 'a';
            if(node.children[index] == null)
                return false;
            node = node.children[index];
        }
        if(!node.isEnd) return false;
        return true;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char chr : prefix.toCharArray()){
            int index = chr - 'a';
            if(node.children[index] == null) return false;
            node = node.children[index];
        }
        return true;
    }
}
