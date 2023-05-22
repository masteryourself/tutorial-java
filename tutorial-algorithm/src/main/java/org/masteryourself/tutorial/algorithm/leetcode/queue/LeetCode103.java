package org.masteryourself.tutorial.algorithm.leetcode.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>description : LeetCode103
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 18:03
 */
public class LeetCode103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.addFirst(root);
        // 偶数表示从左往右, 奇数表示从右往左
        int cycleIndex = 0;
        int nextCycleCount = 1;
        TreeNode treeNode = null;
        while (!treeNodeList.isEmpty()) {
            LinkedList<Integer> tempResult = new LinkedList<>();
            int tempNextCycleCount = 0;
            for (int i = 0; i < nextCycleCount; i++) {
                treeNode = treeNodeList.poll();
                if (cycleIndex % 2 == 1) {
                    tempResult.addFirst(treeNode.val);
                } else {
                    tempResult.addLast(treeNode.val);
                }
                if (treeNode.left != null) {
                    treeNodeList.addLast(treeNode.left);
                    tempNextCycleCount++;
                }
                if (treeNode.right != null) {
                    treeNodeList.addLast(treeNode.right);
                    tempNextCycleCount++;
                }
            }
            nextCycleCount = tempNextCycleCount;
            cycleIndex++;
            result.add(tempResult);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9, null, null),
                new TreeNode(20,
                        new TreeNode(15, null, null),
                        new TreeNode(7, null, null)));
        System.out.println(new LeetCode103().zigzagLevelOrder(root));
    }

}
