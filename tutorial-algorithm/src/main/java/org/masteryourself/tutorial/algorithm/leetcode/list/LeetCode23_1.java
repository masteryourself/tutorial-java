package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode23_1
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 11:51
 */
public class LeetCode23_1 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);
    }

    public ListNode split(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int m = (i + j) >>> 1;
        ListNode left = split(lists, i, m);
        ListNode right = split(lists, m + 1, j);
        return mergeTwoLists(left, right);
    }

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
        ListNode list1 = ListNode.of(1, 4, 5);
        ListNode list2 = ListNode.of(1, 3, 4);
        ListNode list3 = ListNode.of(2, 6);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(new LeetCode23_1().mergeKLists(new ListNode[]{list1, list2, list3}));
    }

}
