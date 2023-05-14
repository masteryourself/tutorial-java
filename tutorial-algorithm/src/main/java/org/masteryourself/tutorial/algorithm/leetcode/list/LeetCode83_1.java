package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode19_1
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 18:48
 */
public class LeetCode83_1 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p2 != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;
                p2 = p2.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 1, 2, 2, 3);
        System.out.println(head);
        System.out.println(new LeetCode83_1().deleteDuplicates(head));
    }

}
