package com.leetcode.hot100.array;

public class _1266_MinTimeToVisitallPoints {
    /**
     * 1266 访问所有点的最小时间
     * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi] 。请你计算访问所有这些点需要的 最小时间（以秒为单位）。
     * 你需要按照下面的规则在平面上移动：
     * 每一秒内，你可以：
     * 沿水平方向移动一个单位长度，或者
     * 沿竖直方向移动一个单位长度，或者
     * 跨过对角线移动 sqrt(2) 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
     * 必须按照数组中出现的顺序来访问这些点。
     * 在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。
     * */
    //如果你 只用水平 + 竖直 需要 dx + dy 秒
    //但你可以用 对角线：每用 1 秒对角线，同时减少 dx 和 dy 各 1
    //那么：能用对角线的次数 = min(dx, dy) 剩下的只能水平或竖直走
    public int minTimeToVisitAllPoints(int[][] points) {
        int minTime = 0;
        int dx = 0;
        int dy = 0;
        for(int i = 1; i < points.length; i++){
            dx = Math.abs(points[i][0] - points[i-1][0]);
            dy = Math.abs(points[i][1] - points[i-1][1]);
            minTime += Math.min(dx, dy) + Math.abs(dx - dy);
        }
        return minTime;
    }
}
