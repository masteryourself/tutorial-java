package org.masteryourself.tutorial.algorithm.leetcode.stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>description : LeetCode225_1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 10:38
 */
public class LeetCode225_1 {

    // q1 用来存储旧元素   2 ->  1
    private Queue<Integer> q1 = new ArrayBlockingQueue<>(100);
    // q2 用来存储新元素
    private Queue<Integer> q2 = new ArrayBlockingQueue<>(100);

    public void push(int x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode225_1 demo = new LeetCode225_1();
        demo.push(1);
        demo.push(2);
        System.out.println(demo.top());
        System.out.println(demo.pop());
        System.out.println(demo.empty());
    }

}
