package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode234
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/15 17:20
 */
public class LeetCode234 {

    public boolean isPalindrome(ListNode head) {
        // 1. 先查找中间链表
        ListNode middleNode = middleNode(head);
        // 2. 将中间链表反转
        ListNode reverseNode = reverse(middleNode);
        // 3. 判断是否相等
        while (reverseNode != null) {
            if (head.val != reverseNode.val) {
                return false;
            }
            reverseNode = reverseNode.next;
            head = head.next;
        }
        return true;
    }

    public ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    public ListNode reverse(ListNode head) {
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
        ListNode list = ListNode.of(1, 2, 3, 2, 1);
        System.out.println(new LeetCode234().isPalindrome(list));
    }

}
