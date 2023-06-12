package org.masteryourself.tutorial.algorithm.leetcode.stack;

import java.util.Stack;

/**
 * <p>description : LeetCode150
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 19:20
 */
public class LeetCode150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+": {
                    Integer num2 = stack.pop();
                    Integer num1 = stack.pop();
                    stack.push(num1 + num2);
                    break;
                }
                case "-": {
                    Integer num2 = stack.pop();
                    Integer num1 = stack.pop();
                    stack.push(num1 - num2);
                    break;
                }
                case "*": {
                    Integer num2 = stack.pop();
                    Integer num1 = stack.pop();
                    stack.push(num1 * num2);
                    break;
                }
                case "/": {
                    Integer num2 = stack.pop();
                    Integer num1 = stack.pop();
                    stack.push(num1 / num2);
                    break;
                }
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode150().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

}
