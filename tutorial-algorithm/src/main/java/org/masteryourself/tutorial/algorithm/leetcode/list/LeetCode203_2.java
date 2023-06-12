package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode203_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 18:17
 */
public class LeetCode203_2 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode nextNode = removeElements(head.next, val);
        if (head.val == val) {
            return nextNode;
        } else {
            head.next = nextNode;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
        System.out.println(head);
        System.out.println(new LeetCode203_2().removeElements(head, 6));
    }

}
