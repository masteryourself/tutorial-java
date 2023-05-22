package org.masteryourself.tutorial.algorithm.leetcode.tree;

import java.util.Arrays;

/**
 * <p>description : LeetCode106
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 18:03
 */
public class LeetCode106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        // 先确定 root 节点
        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                // 中序左子树
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);
                // 后序左子树
                int[] postLeft = Arrays.copyOfRange(postorder, 0, i);
                // 中序右子树
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                // 后序右子树
                int[] postRight = Arrays.copyOfRange(postorder, i, postorder.length - 1);
                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }
        }
        return root;
    }

    /*
         1
        / \
        2  3
       /  / \
      4  6   7
 */
    public static void main(String[] args) {
        new LeetCode106().buildTree(new int[]{4, 2, 1, 6, 3, 7}, new int[]{4, 2, 6, 7, 3, 1});
    }

}
