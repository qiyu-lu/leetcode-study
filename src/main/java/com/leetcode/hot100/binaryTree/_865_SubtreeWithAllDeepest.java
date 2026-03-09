package com.leetcode.hot100.binaryTree;


import java.util.HashMap;

public class _865_SubtreeWithAllDeepest {
    /**
     * 865 具有所有最深节点的最小子树：
     * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
     * 返回包含原始树中所有 最深节点 的 最小子树 。
     * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
     * 一个节点的 子树 是该节点加上它的所有后代的集合。
     * */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).getValue();
    }
    //递归语义：返回当前子树的最大深度以及对应“最深节点所在的最小子树的根”
    //返回二元信息：
    //depth：以 root 为根的子树的最大深度
    //node：包含该子树中所有最深节点的最小子树的根
    private Pair<Integer, TreeNode> dfs(TreeNode root){
        if(root == null) return new Pair<>(0, null);

        Pair<Integer, TreeNode> left = dfs(root.left);
        Pair<Integer, TreeNode> right = dfs(root.right);
        //现在得到当前节点的左子节点为根的子树的最大深度，以及左子树中所有最深节点的最小子树的根
        //和右子树的对应内容，假定得到的正确，

        //挑选最深的那个，一定的最深节点所在的位置，
        if(left.getKey() > right.getKey())
            return new Pair<Integer, TreeNode>(left.getKey()+1, left.getValue());
        else if(left.getKey() < right.getKey())
            return new Pair<Integer, TreeNode>(right.getKey()+1, right.getValue());
        else//左右一样深的话，那么它的最近的公共节点就是当前节点
            return new Pair<Integer, TreeNode>(left.getKey()+1, root);
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {this.val = x;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

class Pair<K,V>{
    K key;
    V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }
}