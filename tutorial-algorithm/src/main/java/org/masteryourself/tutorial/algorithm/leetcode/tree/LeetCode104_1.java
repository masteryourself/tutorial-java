package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode104_1
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 16:40
 */
public class LeetCode104_1 {

    public int maxDepth(TreeNode root) {
        return depth(root);
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return Integer.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                new TreeNode(9), 3,
                new TreeNode(
                        new TreeNode(15),
                        20,
                        new TreeNode(7)));
        System.out.println(new LeetCode104_1().maxDepth(treeNode));
    }

}
