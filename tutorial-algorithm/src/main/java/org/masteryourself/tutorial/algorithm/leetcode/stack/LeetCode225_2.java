package org.masteryourself.tutorial.algorithm.leetcode.stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>description : LeetCode225_2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 11:45
 */
public class LeetCode225_2 {

    private Queue<Integer> queue = new ArrayBlockingQueue<>(100);
    private int size = 0;

    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode225_2 demo = new LeetCode225_2();
        demo.push(1);
        demo.push(2);
        System.out.println(demo.top());
        System.out.println(demo.pop());
        System.out.println(demo.empty());
    }

}
