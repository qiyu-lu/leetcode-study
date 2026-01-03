package com.leetcode.hot100.binaryTree;

public class _105_BuildTree {
    /**
     * 105 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，
     * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * preorder 和 inorder 均 无重复 元素
     * */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //从preorder中找到根节点，然后从inorder中找到左子树
        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

        //3是根节点，从inorder中找到 9 是 3 的左子树，[15 20 7] 是3的右子树
        //9是左子树的根节点，从 inorder中没有找到左子树，就没有子节点
        //20是右根节点，从inorder中查到15是左子树，7是右子树
        //构造一棵树 =
        //    构造根节点
        //    构造左子树
        //    构造右子树
        //而：
        //左子树 = 一棵更小的树
        //右子树 = 一棵更小的树

        //递归函数只负责：
        //给它一段 preorder + inorder，
        //它就帮你构造一棵树，并返回根节点
        // 从整个数组开始构造
        return build(preorder, 0, preorder.length - 1,
                inorder,  0, inorder.length - 1);

    }
    //递归函数只负责：
    //给它一段 preorder + inorder，
    //它就帮你构造一棵树，并返回根节点
    // 构造 preorder[pl..pr] 和 inorder[il..ir] 对应的子树
    private TreeNode build(int[] preorder, int pl, int pr,
                           int[] inorder,  int il, int ir) {

        // ① 递归结束条件：没有节点
        if (pl > pr || il > ir) {
            return null;
        }

        // ② 根节点：preorder 的第一个元素
        int rootVal = preorder[pl];
        TreeNode root = new TreeNode(rootVal);

        // ③ 在 inorder 中找到根节点的位置
        int k = il;
        while (inorder[k] != rootVal) {
            k++;
        }

        // ④ 左子树的节点数量
        int leftSize = k - il;

        // ⑤ 递归构造左子树
        root.left = build(
                preorder,
                pl + 1,
                pl + leftSize,
                inorder,
                il,
                k - 1
        );

        // ⑥ 递归构造右子树
        root.right = build(
                preorder,
                pl + leftSize + 1,
                pr,
                inorder,
                k + 1,
                ir
        );

        // ⑦ 返回当前子树的根
        return root;
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){this.val = x;}
        TreeNode(int x, TreeNode l, TreeNode r){
            this.val = x;
            this.left = l;
            this.right = r;
        }
    }
}
