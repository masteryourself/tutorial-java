package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode98_3
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 15:10
 */
public class LeetCode98_3 {

    /**
     * 对每一个节点都确定上下限
     * root 节点为 (-∞, + ∞)
     * root 左孩子节点为 (-∞, root.val), root 右孩子节点为 (root.val, +∞)
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, Long min, Long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, (long) root.val) && isValidBST(root.right, (long) root.val, max);
    }

}
