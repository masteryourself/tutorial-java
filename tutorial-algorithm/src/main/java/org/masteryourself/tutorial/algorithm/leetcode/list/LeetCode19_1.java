package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode19_1
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 18:48
 */
public class LeetCode19_1 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        findNextIndex(sentinel, n);
        return sentinel.next;
    }

    public int findNextIndex(ListNode node, int n) {
        if (node.next == null) {
            return 0;
        }
        int value = findNextIndex(node.next, n) + 1;
        if (value == n) {
            // 这里不会发生 npe, 因为 node 是待删除节点的上一个节点
            // 假设删除倒数第一个节点, 那么此时的 node 是倒数第2个节点
            node.next = node.next.next;
        }
        return value;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        System.out.println(head);
        System.out.println(new LeetCode19_1().removeNthFromEnd(head, 5));
    }

}
