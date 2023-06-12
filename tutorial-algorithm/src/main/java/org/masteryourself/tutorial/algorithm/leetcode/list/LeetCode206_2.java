package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode206_2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 16:11
 */
public class LeetCode206_2 {

    public ListNode reverseList(ListNode head) {
        List oldList = new List(head);
        List newList = new List(null);
        while (true) {
            ListNode listNode = oldList.removeFirst();
            if (listNode == null) {
                break;
            }
            newList.addFirst(listNode);
        }
        return newList.getHead();
    }

    public class List {

        private ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode node) {
            node.next = head;
            head = node;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }

        public ListNode getHead() {
            return this.head;
        }

    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new LeetCode206_2().reverseList(o1);
        System.out.println(n1);
    }

}
