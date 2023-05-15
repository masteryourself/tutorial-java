package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode206_5
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 17:18
 */
public class LeetCode206_5 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n1 = null;
        ListNode o1 = head;
        while (o1 != null) {
            // 先把 o2 节点取出, 方便最后赋值给 o1
            ListNode o2 = o1.next;
            // 将移动下来的节点的 next 指向 n1
            o1.next = n1;
            // 让 n1 一直保持头节点
            n1 = o1;
            // 让 o1 继续循环
            o1 = o2;
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
        ListNode n1 = new LeetCode206_5().reverseList(o1);
        System.out.println(n1);
    }

}
