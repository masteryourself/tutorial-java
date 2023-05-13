package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode206_1
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 14:23
 */
public class LeetCode206_1 {

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode point = head;
        while (point != null) {
            newHead = new ListNode(point.val, newHead);
            point = point.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new LeetCode206_1().reverseList(o1);
        System.out.println(n1);
    }

}
