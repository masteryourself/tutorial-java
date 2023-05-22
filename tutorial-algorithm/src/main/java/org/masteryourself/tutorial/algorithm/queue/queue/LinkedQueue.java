package org.masteryourself.tutorial.algorithm.queue.queue;

import java.util.Iterator;

/**
 * <p>description : LinkedQueue
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 14:26
 */
public class LinkedQueue<E> implements Queue<E>, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int capacity = Integer.MAX_VALUE;

    public LinkedQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    public LinkedQueue() {
        Node<E> sentinel = new Node<>(null, null);
        head = sentinel;
        tail = sentinel;
        head.next = tail;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removed = head.next;
        head.next = removed.next;
        // 考虑到移除元素就是尾节点(换句话说：移除后队列中只剩下哨兵节点了), 这时候需要更改 tail 指向
        if (removed == tail) {
            tail = head;
        }
        size--;
        return removed.value;
    }

    @Override
    public E peek() {
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> point = head.next;

            @Override
            public boolean hasNext() {
                return point != head;
            }

            @Override
            public E next() {
                E value = point.value;
                point = point.next;
                return value;
            }
        };
    }

    public static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
