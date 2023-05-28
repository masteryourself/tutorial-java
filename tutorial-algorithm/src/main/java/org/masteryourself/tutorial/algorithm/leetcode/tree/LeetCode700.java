package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode700
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 14:02
 */
public class LeetCode700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (val > root.val) {
            return searchBST(root.right, val);
        }
        return root;
    }

}
