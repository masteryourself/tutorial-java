package org.masteryourself.tutorial.algorithm.list;

import java.util.Iterator;

/**
 * <p>description : DoublyLinkedListSentinel
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/11 21:18
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {

    private Node head;
    private Node tail;

    public DoublyLinkedListSentinel() {
        head = new Node(null, -1, null);
        tail = new Node(null, -1, null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void removeFirst() {
        Node removed = head.next;
        if (removed == tail) {
            throw new IllegalArgumentException("非法操作");
        }
        Node next = removed.next;
        head.next = next;
        next.prev = head;
    }

    public void addLast(int value) {
        Node last = tail.prev;
        Node added = new Node(null, value, null);
        last.next = added;
        added.prev = last;
        added.next = tail;
        tail.prev = added;
    }

    public void removeLast() {
        Node removed = tail.prev;
        if (removed == head) {
            throw new IllegalArgumentException("非法操作");
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    public void insert(int index, int value) {
        // 查找前一个节点
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException("非法操作");
        }
        Node next = prev.next;
        Node added = new Node(null, value, null);
        prev.next = added;
        added.prev = prev;
        added.next = next;
        next.prev = added;
    }

    public void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException("非法操作");
        }
        Node removed = prev.next;
        if (removed == tail) {
            throw new IllegalArgumentException("非法操作");
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next) {
            if (i == index) {
                return p;
            }
            i++;
        }
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }

    /**
     * Node 定义为内部类，是为了对外隐藏实现细节，没必要让类的使用者关心 Node 结构
     * 定义为 static 内部类，是因为 Node 不需要与 SinglyLinkedList 实例相关，多个 SinglyLinkedList实例能共用 Node 类定义
     */
    private static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private class NodeIterator implements Iterator<Integer> {
        // head 节点不参与遍历
        Node temp = head.next;

        @Override
        public boolean hasNext() {
            // tail 节点不参与遍历
            return temp != tail;
        }

        @Override
        public Integer next() {
            int value = temp.value;
            temp = temp.next;
            return value;
        }
    }

}
