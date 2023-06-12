package org.masteryourself.tutorial.algorithm.stack;

import java.util.Iterator;

/**
 * <p>description : LinkedListStack
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 17:20
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int capacity;
    private int size;

    private Node<E> head;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head = new Node<>(value, head);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removed = head;
        head = removed.next;
        size--;
        return removed.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.value;
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

            Node<E> nodePoint = head;
            int sizePoint = 0;

            @Override
            public boolean hasNext() {
                return sizePoint < size;
            }

            @Override
            public E next() {
                E value = nodePoint.value;
                nodePoint = nodePoint.next;
                sizePoint++;
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
