package org.masteryourself.tutorial.algorithm.tree.binarytree;

/**
 * <p>description : BinaryTreeRecursion
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/19 19:48
 */
public class BinaryTreeRecursion {

    /**
     * 前序遍历
     * 值左右
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t");
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * 中序遍历
     * 左值右
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + "\t");
        inOrder(root.right);
    }


    /**
     * 后序遍历
     * 左右值
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + "\t");
    }

    /**
     * 1
     * /  \
     * 2    3
     * /    / \
     * 4    5   6
     */
    public static void main(String[] args) {
        BinaryTreeRecursion binaryTreeRecursion = new BinaryTreeRecursion();
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
        System.out.println("前序遍历");
        binaryTreeRecursion.preOrder(root);
        System.out.println();
        System.out.println("中序遍历");
        binaryTreeRecursion.inOrder(root);
        System.out.println();
        System.out.println("后序遍历");
        binaryTreeRecursion.postOrder(root);
    }

}
