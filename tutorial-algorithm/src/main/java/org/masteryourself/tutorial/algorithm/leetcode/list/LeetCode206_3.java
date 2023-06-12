package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode206_3
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 16:47
 */
public class LeetCode206_3 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode returned = reverseList(head.next);
        // 假设此时 p是4, p.next是5, 要让5指向4,代码写成 p.next.next=p
        // 还要注意 4要指向 null, 否则就死链了
        head.next.next = head;
        head.next = null;
        return returned;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new LeetCode206_3().reverseList(o1);
        System.out.println(n1);
    }

}
