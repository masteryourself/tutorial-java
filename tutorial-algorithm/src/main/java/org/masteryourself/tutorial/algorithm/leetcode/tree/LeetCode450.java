package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode450
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/27 18:47
 */
public class LeetCode450 {

    public TreeNode deleteNode(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        // 从左边继续查找
        if (key < node.val) {
            node.left = deleteNode(node.left, key);
            return node;
        }
        // 从右边继续查找
        if (key > node.val) {
            node.right = deleteNode(node.right, key);
            return node;
        }
        // 如果只有左孩子节点
        if (node.right == null) {
            // 这里的返回值是找到了待删除节点的左子节点
            return node.left;
        }
        // 如果只有右孩子节点
        if (node.left == null) {
            // 这里的返回值是找到了待删除节点的右子节点
            return node.right;
        }
        // 如果有左右孩子节点
        // 1. 查找后继节点
        TreeNode successor = node.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        // 2. 此时后继节点的右子树未处理（相当于在删除节点的右子树中删除后继节点，这样后继节点的右子树就会处理完成）
        successor.right = deleteNode(node.right, successor.val);
        // 3. 处理左子树
        successor.left = node.left;
        // 4. 返回后继节点
        return successor;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(n1, 2, n3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(null, 5, n6);
        TreeNode root = new TreeNode(n2, 4, n5);
        new LeetCode450().deleteNode(root, 2);
    }

}
