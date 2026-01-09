package com.leetcode.hot100.graphs;

import java.util.ArrayList;
import java.util.List;

public class _207_CanFinish {
    /**
     * 207 课程表
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
     * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //使用列表存放对应的课程的所有前置课程，然后所有课程的前置课程使用列表数组
        //[{第0个列表存放课程0的所有前置课程}、{第1个列表存放课程1的所有前置课程}...]
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] p : prerequisites){
            graph[p[0]].add(p[1]);
        }

        //用一个数组来表示每个课程的当前状态，有三个状态： 0 未访问， 1 正在访问处理中 2 已经访问完成不会出现环
        int[] state = new int[numCourses];

        //对每一门课程做递归，判断当前课程的前置课程是否会回到自己，即前置课程依赖循环，形成一个环，
        //上面的状态数组就是为了递归时判断是否回到了当前数组，如果到了正在访问的课程就是遇到环了，可以返回false了
        for(int i = 0; i < numCourses; i++){
            if(!dfs(i, graph, state)) return false;
        }
        return true;
    }

    //递归函数：判断从当前课程中出发找前置课程是否会形成环
    private boolean dfs(int course, List<Integer>[] graph, int[] state){
        //如果当前课程正在处理中，即当前课程状态为1，表示当前课程在这条递归路径中，则出现环
        if(state[course] == 1) return false;
        //如果已经检查过了，没问题，那么一定没问题
        if(state[course] == 2) return true;
        //将当前课程标记为正在访问处理中
        state[course] = 1;
        //检查所有前置课程
        for(int i : graph[course]){
            if(!dfs(i, graph, state)) return false;
        }
        //所有前置课程都安全，即不会出现环，标志为2
        state[course] = 2;
        return true;
    }
}