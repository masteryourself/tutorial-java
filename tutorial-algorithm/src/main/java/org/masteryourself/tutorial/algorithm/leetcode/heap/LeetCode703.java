package org.masteryourself.tutorial.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * <p>description : LeetCode703
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 15:28
 */
public class LeetCode703 {

    // 构建一个小顶堆（升序，默认就是升序）
    private int k;
    private PriorityQueue<Integer> heap = null;

    public LeetCode703(int k, int[] nums) {
        this.k = k;
        // 这里限制了堆的容量为 k
        this.heap = new PriorityQueue<>(k);
        // 将数组中的每个元素都添加进去
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (heap.size() < k) {
            heap.offer(val);
            return heap.peek();
        }
        if (heap.peek() > val) {
            return heap.peek();
        }
        heap.poll();
        heap.offer(val);
        return heap.peek();
    }

}
