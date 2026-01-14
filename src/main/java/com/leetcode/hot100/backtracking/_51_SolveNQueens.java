package com.leetcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _51_SolveNQueens {
    /**
     * 51 n皇后
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(board[i], '.');
        }
        boolean[] colsFlag = new boolean[n];//行的标志，标志已经有什么行有皇后
        boolean[] diag1Flag = new boolean[n*2-1];//主方向对角线的标志，标志对角线是否有皇后
        boolean[] diag2Flag = new boolean[n*2-1];//副方向的对角线的标志
        dfs(board, 0, colsFlag, diag1Flag, diag2Flag);
        return res;
    }
    private List<List<String>> res = new ArrayList<>();
    //递归的语义：在前 row 行已经合法放置皇后的前提下，尝试为第 row 行放置皇后。
    private void dfs(char[][] board, int row, boolean[] colsFlag, boolean[] diag1Flag, boolean[] diag2Flag){
        int n = board.length;
        if(row == n){//说明n行都遍历完了 到这里说明前Row行都合法放置了皇后，那就是所有行
            List<String> list = new ArrayList<>();
            for(char[] c : board){
                list.add(new String(c));
            }
            res.add(list);
            return;
        }
        //看row行剩下还有什么地方可以进行选择
        for(int j = 0; j < n; j++){
            if(colsFlag[j])//这列之前有皇后，跳过
                continue;
            if(diag1Flag[row - j + n - 1])//这一条主对角线之前有皇后，跳过
                continue;
            if(diag2Flag[row + j])//这一条副对角线之前有皇后
                continue;
            //到这里说明还有位置可以放合法的皇后
            board[row][j] = 'Q';
            colsFlag[j] = true;
            diag1Flag[row - j + n - 1] = true;
            diag2Flag[row + j] = true;
            dfs(board, row + 1, colsFlag, diag1Flag, diag2Flag);
            board[row][j] = '.';
            colsFlag[j] = false;
            diag1Flag[row - j + n - 1] = false;
            diag2Flag[row + j] = false;
        }
    }
}
