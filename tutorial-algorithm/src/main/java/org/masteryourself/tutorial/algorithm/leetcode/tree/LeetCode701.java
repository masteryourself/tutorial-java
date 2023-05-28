package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode701
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 13:45
 */
public class LeetCode701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode point = root;
        while (point != null) {
            parent = point;
            if (val < point.val) {
                point = point.left;
            } else if (val > point.val) {
                point = point.right;
            }
        }
        if (parent == null) {
            root = new TreeNode(val);
        } else {
            if (parent.val > val) {
                parent.left = new TreeNode(val);
            } else {
                parent.right = new TreeNode(val);
            }
        }
        return root;
    }

}
