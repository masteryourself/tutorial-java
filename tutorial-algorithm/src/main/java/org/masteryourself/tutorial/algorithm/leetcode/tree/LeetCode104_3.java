package org.masteryourself.tutorial.algorithm.leetcode.tree;

import java.util.LinkedList;

/**
 * <p>description : LeetCode104_3
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 17:01
 */
public class LeetCode104_3 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            maxDepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
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
        System.out.println(new LeetCode104_3().maxDepth(treeNode));
    }

}
