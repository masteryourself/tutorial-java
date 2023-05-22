package org.masteryourself.tutorial.algorithm.leetcode.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>description : LeetCode107
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 16:12
 */
public class LeetCode107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int nextCycle = 1;
        while (!queue.isEmpty()) {
            int newNextCycle = 0;
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < nextCycle; i++) {
                TreeNode tempRoot = queue.poll();
                if (tempRoot == null) {
                    continue;
                }
                level.add(tempRoot.val);
                if (tempRoot.left != null) {
                    queue.offer(tempRoot.left);
                    newNextCycle++;
                }
                if (tempRoot.right != null) {
                    queue.offer(tempRoot.right);
                    newNextCycle++;
                }
            }
            nextCycle = newNextCycle;
            result.addFirst(level);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(3,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)));
        System.out.println(new LeetCode107().levelOrderBottom(root));
    }

}
