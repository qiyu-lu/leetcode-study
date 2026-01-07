package com.leetcode.hot100.array;

public class _200_NumIsLands {
    /**
     * 200 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * */
    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    //将岛抹掉，因为陆地的蔓延只能通过上下左右，那么抹掉当前的岛不会影响岛其他的岛
    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
    //这个是对岛进行标记，和上面的思路一样
    private void dfs2(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;

        grid[i][j] = '2';
        dfs2(grid, i - 1, j);
        dfs2(grid, i + 1, j);
        dfs2(grid, i, j - 1);
        dfs2(grid, i, j + 1);
    }
}
