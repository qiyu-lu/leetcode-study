package com.leetcode.hot100.backtracking;

public class _79_Exist {
    /**
     * 79 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
     * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board, i,j, word, 0, visited)) return true;
                }
            }
        }
        return false;
    }
    //“从当前位置开始，能不能把剩下的单词拼完？”  每一层递归都带着一个状态：当前坐标 (i, j) 当前要匹配的字符下标 k
    private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited){
        if(index == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(index)
                || visited[i][j]){
            return false;
        }
        visited[i][j] = true;
        if(dfs(board, i-1, j, word, index+1, visited) ||
                dfs(board, i+1, j, word, index+1, visited) ||
                dfs(board, i, j-1, word, index+1, visited) ||
                dfs(board, i, j+1, word, index+1, visited))
            return true;
        visited[i][j] = false;
        return false;
    }

}
