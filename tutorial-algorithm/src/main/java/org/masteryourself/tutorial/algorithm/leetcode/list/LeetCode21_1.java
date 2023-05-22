package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode21_1
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 10:58
 */
public class LeetCode21_1 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(-1);
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode p3 = sentinel;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            // 新链表也需要向后移动
            p3 = p3.next;
        }
        if (p1 == null) {
            p3.next = p2;
        } else {
            p3.next = p1;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.of(1, 2, 3);
        ListNode list2 = ListNode.of(1, 2, 4);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(new LeetCode21_1().mergeTwoLists(list1, list2));
    }

}
