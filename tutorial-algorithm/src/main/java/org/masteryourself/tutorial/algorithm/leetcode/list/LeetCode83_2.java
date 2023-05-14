package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode83_2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/14 16:41
 */
public class LeetCode83_2 {

    public ListNode deleteDuplicates(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        if (node.val == node.next.val) {
            // 如果值相等, 返回下一个节点
            return deleteDuplicates(node.next);
        } else {
            // next 需要更新, 所以这里 node.next 需要重新赋值
            node.next = deleteDuplicates(node.next);
            return node;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 1, 2, 2, 3);
        System.out.println(head);
        System.out.println(new LeetCode83_2().deleteDuplicates(head));
    }

}
