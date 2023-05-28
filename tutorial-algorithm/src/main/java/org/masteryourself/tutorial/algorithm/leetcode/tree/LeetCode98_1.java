package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode98_1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 14:09
 */
public class LeetCode98_1 {

    /**
     * 利用二叉搜索树的特性判断
     */
    public boolean isValidBST(TreeNode root) {
        // 对于当前树节点来说, 左子树的最大值必须小于当前节点
        if (root.left != null) {
            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            if (leftMax.val >= root.val) {
                return false;
            }
            // 判断左子树是否是树
            if (!isValidBST(root.left)) {
                return false;
            }
        }
        // 对于当前树节点来说, 右子树的最小值必须大于当前节点
        if (root.right != null) {
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            if (rightMin.val <= root.val) {
                return false;
            }
            // 判断右子树是否是树
            if (!isValidBST(root.right)) {
                return false;
            }
        }
        return true;
    }

}
