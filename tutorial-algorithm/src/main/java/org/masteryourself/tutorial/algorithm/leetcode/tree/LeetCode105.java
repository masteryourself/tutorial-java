package org.masteryourself.tutorial.algorithm.leetcode.tree;

import java.util.Arrays;

/**
 * <p>description : LeetCode105
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/20 17:43
 */
public class LeetCode105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        // 1
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                // Arrays.copyOfRange 左闭右开
                // 前序左子树 ==> 2,4
                int[] preLeft = Arrays.copyOfRange(preorder, 1, i + 1);
                // 中序左子树 ==> 4,2
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);
                // 前序右子树 ==> 3,6,7
                int[] preRight = Arrays.copyOfRange(preorder, i + 1, inorder.length);
                // 中序右子树 ==> 6,3,7
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        // 每次返回的都是 root 节点
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
        new LeetCode105().buildTree(new int[]{1, 2, 4, 3, 6, 7}, new int[]{4, 2, 1, 6, 3, 7});
    }

}
