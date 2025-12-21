package com.leetcode.hot100.matrix;

import java.util.ArrayList;
import java.util.List;

public class _54_SpiralOrder {
    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ]
     *  输出 [1,2,3,6,9,8,7,4,5]
     * */
    //顺序就是 第一行，最后一列，最后一行，第一列，的顺序重复进行
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int top = 0;//当前最上面还没有走的行
        int right = n-1; //当前最右边还没有走的列
        int bottom = m-1;//当前最下边还没有走的行
        int left = 0; //当前最左边还没有走的列
        //按照上面的这个顺序进行
        //top=0 第0行   [0,0]-[0,n-1]         n个数
        //right=n-1 第n-1列 [1,n-1]-[m-1,n-1] m-1个数
        //bottom=m-1第m-1行 [m-1,n-2]-[m-1,0] n-1个数
        //left=0    第0列   [m-2,0]-[1,0]     m-2个数

        // top=1  [1,1]-[1,n-2]          n-2个数
        // right=n-2 [2,n-2]-[m-2,n-2]   m-3个数
        // bottom=m-2[m-2,n-3]-[m-2,1]   n-3个数
        // left=1    [m-3,1]-[2,1]       m-4个数
        //总结一下规律
        //top行从 [top,top] - [top,right]
        //right列从 [top+1,right] - [bottom,right]
        //bottom行从 [bottom,right-1]-[bottom,left]
        //left列从   [bottom-1,left]-[top+1,left]
        //解释一下总结的
        //第top行，肯定是从对角线开始的，结束的列就是接下来要走的列right
        //第right列，因为top行和right列有重复元素，那么为了不重复，就从[top+1,right]开始，结束的行就是bottom行
        //第bottom行，同理，right列和bottom列有重复元素，就从[bottom,right-1]开始，结束的列是 left列
        //第left列，同理

        //while中条件，循环中的目的就是不断遍历 这个 人为的 矩形的边，所以这个条件应该是 还有没有“合法矩形区域”存在
        while(top <= bottom && left <= right){
            for(int i = top; i <= right; i++) res.add(matrix[top][i]);
            top++;
            //如果没有区域了就退出，只剩下一行了
            if(top > bottom) break;
            for(int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            //只剩下一列了
            if(left > right) break;
            for(int i = right; i >= left; i--) res.add(matrix[bottom][i]);
            bottom--;
            if(top > bottom) break;
            for(int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            left++;
        }
        return res;
    }
}
