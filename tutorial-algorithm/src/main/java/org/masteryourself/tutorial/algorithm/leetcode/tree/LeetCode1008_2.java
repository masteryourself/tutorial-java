package org.masteryourself.tutorial.algorithm.leetcode.tree;

import java.util.Arrays;

/**
 * <p>description : LeetCode1008_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 16:33
 */
public class LeetCode1008_2 {

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        int index = -1;
        int rootValue = preorder[0];
        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] > rootValue) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootValue);
        // 构造左子树
        int[] leftArray = null;
        // 构造右子树
        int[] rightArray = null;
        if (index == -1) {
            leftArray = Arrays.copyOfRange(preorder, 1, preorder.length);
        } else {
            leftArray = Arrays.copyOfRange(preorder, 1, index);
            rightArray = Arrays.copyOfRange(preorder, index, preorder.length);
        }
        root.left = bstFromPreorder(leftArray);
        root.right = bstFromPreorder(rightArray);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        new LeetCode1008_2().bstFromPreorder(preorder);
    }

}
