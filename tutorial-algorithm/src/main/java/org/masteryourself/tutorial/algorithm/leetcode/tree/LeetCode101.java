package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode101
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 15:46
 */
public class LeetCode101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        // 两者同时为 null
        if (left == null && right == null) {
            return true;
        }
        // 两者只有一个为 null
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(new TreeNode(2), 1, new TreeNode(3));
        System.out.println(new LeetCode101().isSymmetric(treeNode));
    }

}
