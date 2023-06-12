package org.masteryourself.tutorial.algorithm.leetcode.stack;

import java.util.Stack;

/**
 * <p>description : LeetCode232
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 19:20
 */
public class LeetCode232 {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        // 只有当 s2 中的元素为空时, 才能 pop
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        // 只有当 s2 中的元素为空时, 才能 pop
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

}
