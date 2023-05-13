package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode206_4
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 17:01
 */
public class LeetCode206_4 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n1 = head;
        ListNode o1 = head;
        ListNode o2 = head.next;
        while (o2 != null) {
            // 1 -> 3
            o1.next = o2.next;
            // 2 -> n1
            o2.next = n1;
            // n1 赋值
            n1 = o2;
            // o1 一直保持不动, o2 继续移动
            o2 = o1.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new LeetCode206_4().reverseList(o1);
        System.out.println(n1);
    }

}
