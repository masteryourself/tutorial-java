package org.masteryourself.tutorial.algorithm.tree.binarytree;


import java.util.Stack;

/**
 * <p>description : BinaryTreeNoRecursion
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/19 19:48
 */
public class BinaryTreeNoRecursion {

    public void binaryTreeNoRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode point = node;
        TreeNode lastPop = null;
        while (point != null || !stack.isEmpty()) {
            // 1. 先遍历左子树
            if (point != null) {
                colorPrintln("前序遍历》》" + point.val, 31);
                stack.push(point);
                point = point.left;
            } else {
                // 2. 左子树遍历完了, 要先确保右子树遍历完才可以从栈中弹出元素方便, 所以这里用 peek
                TreeNode currentPeek = stack.peek();
                // 2.1 右子树没有节点, 继续弹出下一个元素
                if (currentPeek.right == null) {
                    lastPop = stack.pop();
                    colorPrintln("中序遍历》》》》" + currentPeek.val, 34);
                    colorPrintln("后序遍历》》》》》》" + currentPeek.val, 37);
                }
                // 2.2 右子树节点已经遍历过了
                else if (currentPeek.right == lastPop) {
                    lastPop = stack.pop();
                    colorPrintln("后序遍历》》》》》》" + currentPeek.val, 37);
                }
                // 2.2 右子树有节点, 处理下一个元素
                else {
                    colorPrintln("中序遍历》》》》" + currentPeek.val, 34);
                    point = currentPeek.right;
                }
            }
        }
    }

    public static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    /**
     * 1
     * /  \
     * 2    3
     * /    / \
     * 4    5   6
     */
    public static void main(String[] args) {
        BinaryTreeNoRecursion binaryTreeNoRecursion = new BinaryTreeNoRecursion();
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
        binaryTreeNoRecursion.binaryTreeNoRecursion(root);
    }

}
