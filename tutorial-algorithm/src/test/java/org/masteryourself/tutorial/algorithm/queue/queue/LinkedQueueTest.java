package org.masteryourself.tutorial.algorithm.queue.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : LinkedListQueueTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 14:41
 */
class LinkedQueueTest {

    @Test
    public void offerLimit() {
        LinkedQueue<Integer> queue = new LinkedQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertFalse(queue.offer(4));
        assertFalse(queue.offer(5));

        assertIterableEquals(Arrays.asList(1, 2, 3), queue);
    }

    @Test
    @DisplayName("测试删除只剩一个节点时")
    public void poll1() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.offer(1);
        assertEquals(1, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void offer() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), queue);
    }

    @Test
    public void peek() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        assertNull(queue.peek());
        queue.offer(1);
        assertEquals(1, queue.peek());
        queue.offer(2);
        assertEquals(1, queue.peek());
    }

    @Test
    public void poll() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(4, queue.poll());
        assertEquals(5, queue.poll());
        assertNull(queue.poll());
    }

}