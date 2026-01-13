package com.leetcode.hot100.binarySearch;

public class _3453_SeparateSquares {
    /**
     * 4353 分割正方形 I
     * 给你一个二维整数数组 squares ，其中 squares[i] = [xi, yi, li]
     * 表示一个与 x 轴平行的正方形的左下角坐标和正方形的边长。
     * 找到一个最小的 y 坐标，它对应一条水平线，该线需要满足它以上正方形的总面积 等于 该线以下正方形的总面积。
     * 答案如果与实际答案的误差在 10-5 以内，将视为正确答案。
     * 注意：正方形 可能会 重叠。重叠区域应该被 多次计数 。
     * */
    //这道题的关键就是 注意：正方形 可能会 重叠。重叠区域应该被 多次计数 。 会记录重叠的面积
    //根据给的数组得到了总面积，然后通过计算一侧的面积，可以得到另一侧的面积
    //求一个 Y，使得 f(Y) 恰好等于总面积的一半
    //一开始的想法是推导数学公式，但是正方形的个数为 1 <= squares.length <= 5 * 10e4 数量太多，不好推导
    //单个正方形对Y下方的面积贡献是一个分段函数
    public double separateSquares(int[][] squares) {
        double sumSquares = 0.0;
        double down = Double.POSITIVE_INFINITY;
        double up = Double.NEGATIVE_INFINITY;
        for(int[] square : squares) {
            double side = square[2];
            sumSquares += (side * side);
            down = Math.min(down, square[1]);
            up = Math.max(up, square[1]+side);
        }
        double target = sumSquares / 2.0;
        return binarySearch(squares, down, up, target);
    }
    //检查下半部分地面积是否大于一半
    //之前的想法是获得下半部分的面积，这样通不过测试
    private boolean check(int[][] squares, double Y, double target) {
        double downSquares = 0.0;
        for(int[] square : squares){
            double side = square[2];
            if(square[1] >= Y) continue;
            else if(square[1] + square[2] <= Y) downSquares += side *  side;
            else{
                //有一半是在下面
                downSquares += (Y - square[1]) * side;
            }
        }
        return downSquares >= target;
    }
    private double binarySearch(int[][] squares, double down, double up, double target) {
        while(up - down > 1e-6){
            double mid = (up + down) / 2;

            if(check(squares, mid, target)) {
                up = mid;
            }
            else{
                down = mid;
            }
        }
        return down;
    }
}
