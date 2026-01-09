package com.leetcode.hot100.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class _994_OrangesRotting {
    /**
     * 994 腐烂的橘子
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     * */

    // 一开始的思路是参考 200 题 如下，是错误的代码
    public int orangesRottingError(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        out:
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 2){
                    dfs(grid, i, j);
                    break out;
                }
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    res = -1;
                }
            }
        }
        return res;
    }
    private int res = 0;
    //含义是，传入的是腐烂的橘子，然后让当前位置的上下左右 如果 存在新鲜橘子的话就将其变为腐烂橘子
    private void dfs(int[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0
        || grid[i][j] == 3)
            return;
        if(grid[i][j] == 1 || grid[i][j] == 2) grid[i][j] = 3;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
        res++;
    }

    //这道题和200题不同的是200题关注的是连通性，只要相连通的就会一起处理，而这里不一样，
    //这里需要时间，需要的是先处理当前腐烂橘子的周围一圈，然后再处理第二圈这样才能保证时间的递增
    //一层一层向外扩散,这在算法里叫：层序遍历 / 波纹扩散 / 最短距离
    //DFS 天生不具备这个能力
    //BFS 天生就是为这个设计的
    //这是正确的代码：
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int time = -1;
        Queue<int[]> queue = new LinkedList<>();
        boolean flag = false;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 2){
                    queue.add(new int[]{i, j});
                }
                if(grid[i][j] == 1) flag = true;
            }
        }
        if(!flag) return 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int curI = cur[0];
                int curJ = cur[1];
                if(grid[curI][curJ] == 1) grid[curI][curJ] = 2;
                if(curI-1 >= 0 && curI-1 < row && grid[curI-1][curJ] == 1){
                    grid[curI-1][curJ] = 2;
                    queue.add(new int[]{curI-1, curJ});
                }
                if(curI+1 >= 0 && curI+1 < row && grid[curI+1][curJ] == 1) {
                    grid[curI+1][curJ] = 2;
                    queue.add(new int[]{curI+1, curJ});
                }
                if(curJ-1 >= 0 && curJ-1 < col && grid[curI][curJ-1] == 1){
                    grid[curI][curJ-1] = 2;
                    queue.add(new int[]{curI, curJ-1});
                }
                if(curJ+1 >= 0 && curJ+1 < col && grid[curI][curJ+1] == 1) {
                    grid[curI][curJ+1] = 2;
                    queue.add(new int[]{curI, curJ + 1});
                }
            }
            time++;
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1) return -1;
            }
        }
        return time;
    }
}
