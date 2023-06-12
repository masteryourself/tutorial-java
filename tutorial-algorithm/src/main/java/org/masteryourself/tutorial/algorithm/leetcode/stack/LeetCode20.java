package org.masteryourself.tutorial.algorithm.leetcode.stack;

import java.util.Stack;

/**
 * <p>description : LeetCode20
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 18:20
 */
public class LeetCode20 {

    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (char c : charArray) {
            String value = String.valueOf(c);
            if (value.equals("(")) {
                stack.push(")");
            } else if (value.equals("[")) {
                stack.push("]");
            } else if (value.equals("{")) {
                stack.push("}");
            } else {
                if (!stack.isEmpty() && value.equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode20().isValid("()[]{}"));
        System.out.println(new LeetCode20().isValid("]"));
    }

}
