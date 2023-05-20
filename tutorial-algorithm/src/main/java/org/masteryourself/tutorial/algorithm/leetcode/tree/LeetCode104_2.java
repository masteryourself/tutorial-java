package org.masteryourself.tutorial.algorithm.leetcode.tree;

import java.util.Stack;

/**
 * <p>description : LeetCode104_2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 16:51
 */
public class LeetCode104_2 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = -1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode point = root;
        TreeNode lastPop = null;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                maxDepth = Math.max(maxDepth, stack.size());
                point = point.left;
            } else {
                TreeNode peek = stack.peek();
                // 如果右子树为空或者已经遍历过, 则从队列中取出来
                if (peek.right == null || peek.right == lastPop) {
                    lastPop = stack.pop();
                } else {
                    point = peek.right;
                }
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                new TreeNode(9), 3,
                new TreeNode(
                        new TreeNode(15),
                        20,
                        new TreeNode(7)));
        System.out.println(new LeetCode104_2().maxDepth(treeNode));
    }

}
