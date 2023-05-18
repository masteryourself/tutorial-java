package org.masteryourself.tutorial.algorithm.leetcode.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>description : LeetCode23_2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 20:49
 */
public class LeetCode23_2 {

    public ListNode mergeKLists(ListNode[] lists) {
        // 升序使用大顶堆, 降序使用小顶堆
        // 这里使用小顶堆, 因为要取出最小的元素用来放到新的链表中
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        ListNode sentinel = new ListNode(-1, null);
        ListNode point = sentinel;
        while (!heap.isEmpty()) {
            // 从小顶堆中取出最小元素
            ListNode ele = heap.poll();
            // 将取出的元素.next 放入堆中比较
            if (ele.next != null) {
                heap.offer(ele.next);
            }
            point.next = ele;
            point = ele;
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
        System.out.println(new LeetCode23_2().mergeKLists(new ListNode[]{list1, list2, list3}));
    }

}
