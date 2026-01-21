package com.leetcode.hot100.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _295_MedianFinder {
    /**
     * 295 数据流的中位数
     * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
     * 例如 arr = [2,3,4] 的中位数是 3 。
     * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
     * 实现 MedianFinder 类:
     * MedianFinder() 初始化 MedianFinder 对象。
     * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
     * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
     * */

    //我的思路是中位数就是 n 个数，
    //如果n是奇数就是，前(n+1)/2个最大的数中最小的那个就是中位数，或者前前(n+1)/2个最小的数中最大的就是
    //n是偶数，前n/2 + 1即 (n+1)/2+1  个最大的数中 最小和次小的数的平均数就是中位数，或者最小中...
    //就是先找(n+1)/2个最大的数中最小的，再找 (n+1)/2+1个最大的数中最小的
    //思路就是先找 前 (n+1)/2 个最大或者最小的数，如果是奇数，中位数就出来了，如果是偶数再找一个比当前的最值还要大(小)的数
    //现在添加了一个数，变为偶数了，那么就需要向堆中添加一个数，但是有问题，
    //假如原来的是 1 2 3 ，堆中就是 2 3 ，现在要添加4的话就会是2 3 4，最小的还是2，这就有问题了

    //正确的思路是使用双堆（大顶堆 + 小顶堆）大顶堆存储n个数中小的一部分,小顶堆存储n个数中大的一部分
    //n个数为偶数的话，大顶堆存储前n/2个最小数，堆顶为其最小值，小顶堆存储 n/2 个最大数，堆顶为其最小值
    //当前的中位数就是两个堆顶的平均数
    //n如果为奇数的话，

            //新加如一个数，如果原来有偶数个数，新加入的数就和两个堆的堆顶进行比较，如果
    //小顶堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //大顶堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> b -a
    );

    public _295_MedianFinder() {

    }

    public void addNum(int num) {
        //大顶堆存储n个数中小的一部分,堆顶为前几个小的数中最大的那个
        //小顶堆存储n个数中大的一部分,堆顶为前几个大的数中最小的那个
        //目标：保证大顶堆 ≤ 小顶堆，且堆大小差 ≤ 1
        if(maxHeap.isEmpty() || num < maxHeap.peek()){
            //当前数属于小的一部分
            maxHeap.offer(num);
        }
        else{
            //当前数大于等于前几个小的数中的最大值，那么就属于小顶堆
            minHeap.offer(num);
        }
        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        }
        else if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if((maxHeap.size() + minHeap.size()) % 2 == 0){
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else{
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }
}

class MedianFinder {
    private PriorityQueue<Integer> maxHeap =  new PriorityQueue<>(
            (a, b) -> b - a
    );
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public MedianFinder() {

    }
    //可以直接根据数的个数来判断，不需要判断堆顶
    public void addNum(int num) {
        if(maxHeap.size() == minHeap.size()){
            //原来是偶数，如果规则设定奇数的话从小顶堆中获取的话就是下面的：

            //先将数添加到大顶堆中，大顶堆的堆顶是前 n/2个最小的数中的最大的，
            //然后将大顶堆的堆顶放入到小顶堆中，
            //如果直接放入到小顶堆中不确定这个新加入的数是否是前n/2 个最大的数
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        else{
            //原来是奇数,奇数的话小顶堆的多，大顶堆的少
            //如果直接将这个数加入大顶堆的话不对，因为这样加入的这个数可能不是前 n/2 个最小的数
            //因此需要先将这个数加入到小顶堆中，此时小顶堆中的堆顶为前 n/2 个最大的数中最小的，
            //将这个最小的加入到大顶堆中
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
