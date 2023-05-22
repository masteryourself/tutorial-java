package org.masteryourself.tutorial.algorithm.stack;

import java.util.Iterator;

/**
 * <p>description : ArrayStack
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 17:21
 */
public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private int capacity;
    private int topIndex;
    private E[] array = null;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[topIndex++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return array[--topIndex];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[topIndex - 1];
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topIndex == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = topIndex - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return array[index--];
            }
        };
    }

}
