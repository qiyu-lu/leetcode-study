package com.leetcode.hot100.binaryTree;

public class _1339_MaxProduct {
    /**
     * 1339 分裂二叉树的最大乘积
     * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，
     * 使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
     * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
     * */
    //我的最初的想法是 参考124题，通过计算当前节点的左右子树的所有节点的和，然后维护一个最大的乘积
    //但是这样会有一个问题，这个最大的乘积没有包含当前节点上一层的信息。
    //比如4层的树最大乘积 需要 断掉第二层节点和第三层节点的边，我的这种方法，会导致少了,就是在sum函数中直接维护一个最大的乘积，但是没有其他节点的信息。
    //为了正确，就需要知道当前节点子树以外的节点的和，而直接计算会难以思考，可以先计算所有的节点和，然后再减去
    public int maxProduct(TreeNode root) {
        long sum = sum(root);
        //getRes(root, sum);
        getRes2(root, sum);
        return (int)(res % 1000000007);
    }
    private long res = 0;
    //递归语义，124题返回当前root节点的所有节点的值的和
    private long sum(TreeNode root){
        if(root == null) return 0;

        long left = sum(root.left);//得到左子树的所有节点的和
        long right = sum(root.right);//得到右子树的所有节点的和

        //先返回当前层的正确结果
        return left+right+root.val;
    }
    //递归一，就是当前节点的两个子树分别尝试切断然后判断最大乘积
    private long getRes(TreeNode root, long sum){
        if(root == null) return 0;

        long left = getRes(root.left, sum);//得到左子树的所有节点的和
        long right = getRes(root.right, sum);//得到右子树的所有节点的和

        //假设递归正确，即left和right计算正确

        //维护一个最大乘积
        //现在知道左子树的所有节点和 右子树的所有节点和 以及 当前根节点的值，那么如何计算这个最大乘积呢
        //题意知道值是大于0的，不用考虑负数的问题。

        long product1 = left * (sum - left)  ;//left * (right + root.val + sum -left - right -root.val) = left * (sum - left)
        long product2 = right * (sum - right);
        long tempMaxProduct = Math.max(product1, product2);
        res = Math.max(res, tempMaxProduct);

        //先返回当前层的正确结果
        return left + right + root.val;
    }
    //这个递归是尝试将以root为根节点的子树都切掉，
    private long getRes2(TreeNode root, long sum){
        if(root == null) return 0;

        long left = getRes2(root.left, sum);//得到左子树的所有节点的和
        long right = getRes2(root.right, sum);//得到右子树的所有节点的和

        //假设递归正确，即left和right计算正确

        //维护一个最大乘积
        //现在知道左子树的所有节点和 右子树的所有节点和 以及 当前根节点的值，那么如何计算这个最大乘积呢
        //题意知道值是大于0的，不用考虑负数的问题。
        long curSum = left + right + root.val;
        res = Math.max(res, curSum * (sum - curSum));

        //先返回当前层的正确结果
        return left + right + root.val;
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
