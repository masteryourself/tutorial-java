package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode938
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 15:23
 */
public class LeetCode938 {

    int sum = 0;

    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return sum;
        }
        if (node.val >= low) {
            // 只有当前值 > 最小值, 左遍历才有可能会继续大于最小值
            rangeSumBST(node.left, low, high);
        }
        if (node.val >= low && node.val <= high) {
            sum += node.val;
        }
        if (node.val <= high) {
            // 只有当前值 < 最大值, 右遍历才有可能会继续小于最大值
            rangeSumBST(node.right, low, high);
        }
        return sum;
    }

}
