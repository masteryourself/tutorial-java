package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode203_1
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 18:00
 */
public class LeetCode203_1 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel;
        ListNode p2 = sentinel.next;
        while (p2 != null) {
            // 判断是否需要删除
            if (p2.val == val) {
                p1.next = p2.next;
                p2 = p2.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
        System.out.println(head);
        System.out.println(new LeetCode203_1().removeElements(head, 6));
    }

}
