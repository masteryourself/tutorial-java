package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode876
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 17:09
 */
public class LeetCode876 {

    public ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.of(1, 2, 3, 4, 5, 6);
        System.out.println(new LeetCode876().middleNode(list));
    }

}
