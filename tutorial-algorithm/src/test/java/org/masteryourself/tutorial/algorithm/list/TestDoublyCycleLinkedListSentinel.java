package org.masteryourself.tutorial.algorithm.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <p>description : TestDoublyCycleLinkedListSentinel
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/12 14:30
 */
public class TestDoublyCycleLinkedListSentinel {

    @Test
    void test() {
        DoublyCycleLinkedListSentinel list = new DoublyCycleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.remove(3);
    }

    @Test
    void addFirst() {
        DoublyCycleLinkedListSentinel list = new DoublyCycleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        assertIterableEquals(Arrays.asList(4, 3, 2, 1), list);
    }

    @Test
    void removeFirst() {
        DoublyCycleLinkedListSentinel list = getList();
        list.removeFirst();
        assertIterableEquals(Arrays.asList(2, 3, 4), list);
        list.removeFirst();
        assertIterableEquals(Arrays.asList(3, 4), list);
        list.removeFirst();
        assertIterableEquals(Arrays.asList(4), list);
        list.removeFirst();
        assertIterableEquals(Arrays.asList(), list);
        assertThrows(IllegalArgumentException.class, list::removeFirst);
    }

    @Test
    void addLast() {
        DoublyCycleLinkedListSentinel list = getList();
        assertIterableEquals(Arrays.asList(1, 2, 3, 4), list);
    }

    private DoublyCycleLinkedListSentinel getList() {
        DoublyCycleLinkedListSentinel list = new DoublyCycleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }

    @Test
    void removeLast() {
        DoublyCycleLinkedListSentinel list = getList();
        list.removeLast();
        assertIterableEquals(Arrays.asList(1, 2, 3), list);
        list.removeLast();
        assertIterableEquals(Arrays.asList(1, 2), list);
        list.removeLast();
        assertIterableEquals(Arrays.asList(1), list);
        list.removeLast();
        assertIterableEquals(Arrays.asList(), list);
        assertThrows(IllegalArgumentException.class, list::removeLast);
    }

    @Test
    void insert() {
        DoublyCycleLinkedListSentinel list = getList();
        list.insert(2, 5);
        assertIterableEquals(Arrays.asList(1, 2, 5, 3, 4), list);
        list.insert(5, 6);
        assertIterableEquals(Arrays.asList(1, 2, 5, 3, 4, 6), list);
        assertThrows(IllegalArgumentException.class, () -> list.insert(7, 7));
    }

    @Test
    void remove() {
        DoublyCycleLinkedListSentinel list = getList();
        list.remove(2);
        assertIterableEquals(Arrays.asList(1, 2, 4), list);
        assertThrows(IllegalArgumentException.class, () -> list.remove(10));

        DoublyLinkedListSentinel list2 = new DoublyLinkedListSentinel();
        assertThrows(IllegalArgumentException.class, () -> list2.remove(0));
    }

}
