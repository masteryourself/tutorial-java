package org.masteryourself.tutorial.algorithm.leetcode.tree;

/**
 * <p>description : LeetCode235
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/28 17:58
 */
public class LeetCode235 {

    /**
     * 结论：待查找节点 p、q 在某一节点的两侧，那么此节点就是最近的公共元素
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode point = root;
        while (point != null) {
            // 如果都比 root 小, 就向左侧查找
            if (p.val < point.val && q.val < point.val) {
                point = point.left;
            }
            // 如果都比 root 大, 就向右侧查找
            else if (p.val > point.val && q.val > point.val) {
                point = point.right;
            } else {
                break;
            }
        }
        return point;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(0), 2, new TreeNode(new TreeNode(3), 4, new TreeNode(5))),
                6,
                new TreeNode(new TreeNode(7), 8, new TreeNode(9)));
        TreeNode t1 = new LeetCode235().lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8));
        System.out.println(t1.val); // 应为 6
        TreeNode t2 = new LeetCode235().lowestCommonAncestor(root, new TreeNode(4), new TreeNode(5));
        System.out.println(t2.val); // 应为 4
    }

}
