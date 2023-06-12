package org.masteryourself.tutorial.algorithm.queue.deque;

import java.util.Iterator;

/**
 * <p>description : LinkedDeque
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 15:25
 */
public class LinkedDeque<E> implements Deque<E>, Iterable<E> {

    private int size;
    private int capacity;
    private Node<E> sentinel = new Node<>(null, null, null);

    public LinkedDeque(int capacity) {
        this.capacity = capacity;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        // s(a) added b
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        // a added s(b)
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        // a(s) removed b
        Node<E> a = sentinel;
        Node<E> removed = a.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        // a(s) removed b
        Node<E> removed = sentinel.prev;
        Node<E> a = removed.prev;
        Node<E> b = sentinel;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> point = sentinel.next;

            @Override
            public boolean hasNext() {
                return point != sentinel;
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
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

}
