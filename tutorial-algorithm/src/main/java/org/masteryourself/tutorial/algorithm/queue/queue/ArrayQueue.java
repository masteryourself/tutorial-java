package org.masteryourself.tutorial.algorithm.queue.queue;

import java.util.Iterator;

/**
 * <p>description : ArrayQueue
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 15:43
 */
public class ArrayQueue<E> implements Queue<E>, Iterable<E> {

    // 使用 head 与 tail 计算索引
    private int head = 0;
    private int tail = 0;
    private E[] array = null;

    public ArrayQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail % array.length] = value;
        // 这里未考虑溢出
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head % array.length];
        // 这里未考虑溢出
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head % array.length];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
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
                E value = array[point];
                point++;
                return value;
            }
        };
    }

}
