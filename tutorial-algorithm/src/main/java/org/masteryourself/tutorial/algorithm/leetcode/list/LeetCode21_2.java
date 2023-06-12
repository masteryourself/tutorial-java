package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode21_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 11:26
 */
public class LeetCode21_2 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.of(1, 2, 3);
        ListNode list2 = ListNode.of(1, 2, 4);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(new LeetCode21_2().mergeTwoLists(list1, list2));
    }

}
