package org.masteryourself.tutorial.algorithm.leetcode.list;

/**
 * <p>description : LeetCode237
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 18:48
 */
public class LeetCode237 {

    public void deleteNode(ListNode node) {
        // 下一个节点值赋值给待"删除"节点
        node.val = node.next.val;
        // 把下一个节点删除
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        new LeetCode237().deleteNode(o3);
        System.out.println(o1);
    }

}
