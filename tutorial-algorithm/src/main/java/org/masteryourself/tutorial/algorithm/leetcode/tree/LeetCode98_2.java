package org.masteryourself.tutorial.algorithm.leetcode.tree;


import java.util.Stack;

/**
 * <p>description : LeetCode98_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 15:04
 */
public class LeetCode98_2 {

    /**
     * 使用中序遍历判断, 中序遍历是一个有序数组
     * 这里可以使用递归和非递归方法
     */
    public boolean isValidBST(TreeNode root) {
        TreeNode point = root;
        Stack<TreeNode> stack = new Stack<>();
        Integer preVal = null;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                point = point.left;
            } else {
                TreeNode pop = stack.pop();
                if (preVal != null && preVal >= pop.val) {
                    return false;
                }
                preVal = pop.val;
                point = pop.right;
            }
        }
        return true;
    }

}
