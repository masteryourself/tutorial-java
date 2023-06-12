package org.masteryourself.tutorial.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * <p>description : LeetCode295
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 14:51
 */
public class LeetCode295 {

    // 大顶堆（降序）
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    // 小顶堆（升序，默认就是升序）
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public LeetCode295() {

    }

    /**
     * 左边：大顶堆, 右边：小顶堆
     * 为了保证两边数据的平衡
     * 两边个数一样时, 左边个数 +1（新元素加在右边，然后挑选右边最小的加入）
     * 两边个数不一样时, 右边个数 +1（新元素加在左边，然后挑选左边最大的加入）
     *
     * @param num
     */
    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

}
