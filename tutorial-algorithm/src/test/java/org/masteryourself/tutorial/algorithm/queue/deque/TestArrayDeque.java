package org.masteryourself.tutorial.algorithm.queue.deque;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : TestArrayDeque
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 17:47
 */
public class TestArrayDeque {

    @Test
    public void offer() {
        ArrayDeque<Integer> deque = new ArrayDeque<>(3);
        // 2 1 3
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerLast(3);
        assertFalse(deque.offerLast(4));
        assertIterableEquals(Arrays.asList(2, 1, 3), deque);
    }

    @Test
    public void poll() {
        ArrayDeque<Integer> deque = new ArrayDeque<>(7);
        assertTrue(deque.isEmpty());

        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);
        deque.offerFirst(4);
        deque.offerFirst(5);
        deque.offerFirst(6);
        deque.offerFirst(7);
        assertIterableEquals(Arrays.asList(7, 6, 5, 4, 1, 2, 3), deque);
        assertTrue(deque.isFull());

        assertEquals(7, deque.pollFirst());
        assertEquals(6, deque.pollFirst());
        assertEquals(3, deque.pollLast());
        assertEquals(2, deque.pollLast());
        assertEquals(1, deque.pollLast());
        assertEquals(4, deque.pollLast());
        assertEquals(5, deque.pollLast());
        assertNull(deque.pollLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void peek() {
        ArrayDeque<Integer> deque = new ArrayDeque<>(7);
        // 3 1 2 4
        deque.offerFirst(1);
        deque.offerLast(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        assertEquals(4, deque.peekLast());
        assertEquals(3, deque.peekFirst());
    }

}
