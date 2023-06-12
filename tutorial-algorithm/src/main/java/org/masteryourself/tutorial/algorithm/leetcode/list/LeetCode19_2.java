package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode19_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 19:14
 */
public class LeetCode19_2 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel;
        ListNode p2 = sentinel;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        System.out.println(head);
        System.out.println(new LeetCode19_2().removeNthFromEnd(head, 5));
    }

}
