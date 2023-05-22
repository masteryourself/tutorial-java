package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode142
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 10:41
 */
public class LeetCode142 {

    public ListNode detectCycle(ListNode head) {
        // 🐰位置保持不变
        ListNode p2 = findMeetPosition(head);
        // 🐢重新走
        ListNode p1 = head;
        if (p2 == null) {
            return null;
        }
        while (true) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;

        }
    }

    private ListNode findMeetPosition(ListNode head) {
        // 🐢
        ListNode p1 = head;
        // 🐰
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // 构造一个带环链表
        ListNode n3 = new ListNode(3, null);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        n3.next = n1;
        System.out.println(new LeetCode142().detectCycle(n1).val);
    }

}
