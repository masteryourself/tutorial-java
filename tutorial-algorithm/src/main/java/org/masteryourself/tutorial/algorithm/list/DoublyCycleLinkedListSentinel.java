package org.masteryourself.tutorial.algorithm.list;

import java.util.Iterator;

/**
 * <p>description : DoublyCycleLinkedListSentinel
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/12 14:11
 */
public class DoublyCycleLinkedListSentinel implements Iterable<Integer> {

    private final Node sentinel = new Node(null, -1, null);

    public DoublyCycleLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(int value) {
        Node next = sentinel.next;
        Node added = new Node(null, value, null);
        sentinel.next = added;
        added.prev = sentinel;
        next.prev = added;
        added.next = next;
    }

    public void removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            throw new IllegalArgumentException("非法操作");
        }
        Node next = removed.next;
        sentinel.next = next;
        next.prev = sentinel;
    }

    public void addLast(int value) {
        Node prev = sentinel.prev;
        Node added = new Node(null, value, null);
        prev.next = added;
        added.prev = prev;
        added.next = sentinel;
        sentinel.prev = added;
    }

    public void removeLast() {
        Node removed = sentinel.prev;
        if(removed == sentinel){
            throw new IllegalArgumentException("非法操作");
        }
        Node prev = removed.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
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
        Node removed = findNode(index);
        if (removed == null) {
            throw new IllegalArgumentException("非法操作");
        }
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = next;
    }

    private Node findNode(int index) {
        int i = 0;
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
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
        Node temp = sentinel.next;

        @Override
        public boolean hasNext() {
            // tail 节点不参与遍历
            return temp != sentinel;
        }

        @Override
        public Integer next() {
            int value = temp.value;
            temp = temp.next;
            return value;
        }
    }

}
