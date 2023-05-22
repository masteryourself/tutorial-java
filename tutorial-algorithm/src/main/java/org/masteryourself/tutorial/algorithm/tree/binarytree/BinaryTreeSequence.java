package org.masteryourself.tutorial.algorithm.tree.binarytree;

import java.util.LinkedList;

/**
 * <p>description : BinaryTreeSequence
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 15:32
 */
public class BinaryTreeSequence {

    public void sequence(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode pop = queue.poll();
            System.out.print(pop.val + "\t");
            if (pop.left != null) {
                queue.offer(pop.left);
            }
            if (pop.right != null) {
                queue.offer(pop.right);
            }
        }
    }

    /**
     * 1
     * /  \
     * 2    3
     * /    / \
     * 4    5   6
     */
    public static void main(String[] args) {
        BinaryTreeSequence binaryTreeSequence = new BinaryTreeSequence();
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(null, 4, null),
                        2
                        , null),
                1,
                new TreeNode(
                        new TreeNode(null, 5, null),
                        3,
                        new TreeNode(null, 6, null))
        );
        binaryTreeSequence.sequence(root);
    }

}
