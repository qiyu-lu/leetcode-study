package com.leetcode.hot100.binaryTree;

public class _236_LowestCommonAncestor {
    /**
     * 236 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
     * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * */
    //最近的公共祖先一定在第一次分叉的地方，如果从根节点向下找，当前节点
    //p 和 q 在同一侧子树上，那么现在还没到最近，但已经是祖先节点了，那么最近的公共祖先也在这一侧子树上
    //p 和 q 不在同一侧子树上，那么当前节点就是最近的公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root,p,q);
    }
    //递归的任务：对每个节点，需要判断：
    //当前节点是否是 p
    //当前节点是否是 q
    //p 和 q 分别在不在 root 的左右子树中

    //若当前节点为 p 或 q，返回该节点
    //递归左右子树
    //若左右子树均返回非空，当前节点即为最近公共祖先
    //否则返回非空的一侧

    //递归语义是：
    //如果在以 root 为根的子树中，找到了 p 或 q，
    //返回找到的那个节点.返回这个节点是为了后续的判断
    //如果 p 和 q 分别在左右子树，
    //返回它们的最近公共祖先
    //如果 都没找到，
    //返回 null
    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = find(root.left,p,q);//从当前根节点的左子树中找
        TreeNode right = find(root.right,p,q);//从当前根节点的右子树中找
        //两个都不为空，说明分别在两侧的子树上找到了两个对应的节点，
        // 说明当前根节点就是那个分叉点，
        if(left != null && right != null) return root;

        else if(left != null) return left;
        else return right;
    }



    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
