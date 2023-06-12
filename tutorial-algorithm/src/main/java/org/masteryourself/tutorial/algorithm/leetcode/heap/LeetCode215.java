package org.masteryourself.tutorial.algorithm.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>description : LeetCode215
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 14:51
 */
public class LeetCode215 {

    public int findKthLargest(int[] nums, int k) {
        // 这里使用小顶堆（升序）
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        // 先将前 K 个元素放入堆中
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        // 将最小的元素与剩下的元素比较, 如果 > 顶端元素就替换
        for (int i = k; i < nums.length; i++) {
            if (queue.peek() > nums[i]) {
                continue;
            }
            // 没有 replace() 方法, 只好先 poll() 再 offer()
            queue.poll();
            queue.offer(nums[i]);
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

}
