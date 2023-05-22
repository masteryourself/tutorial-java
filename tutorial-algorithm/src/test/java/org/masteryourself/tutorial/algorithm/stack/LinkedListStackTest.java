package org.masteryourself.tutorial.algorithm.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : LinkedListStackTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 17:36
 */
class LinkedListStackTest {

    @Test
    public void push() {
        LinkedListStack<Object> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse(stack.push(4));
        assertIterableEquals(Arrays.asList(3, 2, 1), stack);
    }

    @Test
    public void pop() {
        LinkedListStack<Object> stack = new LinkedListStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // 3, 2, 1
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertNull(stack.pop());
    }

}
