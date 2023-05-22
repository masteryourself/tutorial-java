package org.masteryourself.tutorial.algorithm.queue.deque;

import java.util.Iterator;

/**
 * <p>description : ArrayDeque
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 17:26
 */
public class ArrayDeque<E> implements Deque<E>, Iterable<E> {

    // 先--, 再添加值
    private int head = 0;
    // 先添加值, 再++
    private int tail = 0;
    private E[] array;

    public ArrayDeque(int capacity) {
        this.array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        head = decIndex(head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = incIndex(tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = incIndex(head, array.length);
        return value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = decIndex(tail, array.length);
        return array[tail];
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[decIndex(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == array.length - 1;
        } else if (tail < head) {
            return head - tail == 1;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int point = head;

            @Override
            public boolean hasNext() {
                return point != tail;
            }

            @Override
            public E next() {
                E e = array[point];
                point = incIndex(point, array.length);
                return e;
            }
        };
    }

    public static int incIndex(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        } else {
            return i + 1;
        }
    }

    public static int decIndex(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        } else {
            return i - 1;
        }
    }

}
