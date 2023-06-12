package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode82_1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/14 18:12
 */
public class LeetCode82_1 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel;
        ListNode p2;
        ListNode p3;
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while (p3 != null && p2.val == p3.val) {
                    p3 = p3.next;
                }
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 1);
        System.out.println(head);
        System.out.println(new LeetCode82_1().deleteDuplicates(head));
    }

}
