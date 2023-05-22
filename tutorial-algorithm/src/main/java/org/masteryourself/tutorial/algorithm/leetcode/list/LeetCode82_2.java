package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode82_2
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 10:39
 */
public class LeetCode82_2 {

    public ListNode deleteDuplicates(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        if (node.val == node.next.val) {
            ListNode point = node.next.next;
            while (point != null && point.val == node.val) {
                point = point.next;
            }
            return deleteDuplicates(point);
        } else {
            node.next = deleteDuplicates(node.next);
            return node;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 3, 3, 4, 5);
        System.out.println(head);
        System.out.println(new LeetCode82_2().deleteDuplicates(head));
    }

}
