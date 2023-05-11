package org.masteryourself.tutorial.algorithm.list;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * <p>description : SinglyLinkedList
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/11 18:12
 */
public class SinglyLinkedList implements Iterable<Integer> {

    private Node head;

    /**
     * 如果 this.head == null，新增节点指向 null，并作为新的 this.head
     * 如果 this.head != null，新增节点指向原来的 this.head，并作为新的 this.head
     *
     * @param value 待添加值
     */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    /**
     * while 遍历
     *
     * @param consumer 要执行的操作
     */
    public void loop1(Consumer<Integer> consumer) {
        Node curr = head;
        while (curr != null) {
            consumer.accept(curr.value);
            curr = curr.next;
        }
    }

    /**
     * 遍历链表2
     *
     * @param consumer 要执行的操作
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node curr = head; curr != null; curr = curr.next) {
            consumer.accept(curr.value);
        }
    }

    /**
     * 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }

    /**
     * 根据索引查找
     *
     * @param index 索引
     * @return 找到, 返回该索引位置节点的值
     * @throws IllegalArgumentException 找不到, 抛出 index 非法异常
     */
    public int get(int index) throws IllegalArgumentException {
        Node current = findNode(index);
        if (current == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        return current.value;
    }

    /**
     * 向索引位置插入
     *
     * @param index 索引
     * @param value 待插入值
     * @throws IllegalArgumentException 找不到, 抛出 index 非法异常
     */
    public void insert(int index, int value) throws IllegalArgumentException {
        if (index == 0) {
            addFirst(value);
            return;
        }
        // 先找到插入索引的前一个位置
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        // 初始化当前节点, 它的下一个节点是 prev.next
        Node current = new Node(value, prev.next);
        // 设置前一个节点的 next = current
        prev.next = current;
    }

    /**
     * 向链表尾部添加
     *
     * @param value 待添加值
     */
    public void addLast(int value) {
        // 如果 head 为空, 即是加入头节点
        if (head == null) {
            addFirst(value);
            return;
        }
        Node lastNode = findLastNode();
        lastNode.next = new Node(value, null);
    }

    /**
     * 删除第一个
     *
     * @throws IllegalArgumentException - 如果不存在, 抛出 index 非法异常
     */
    public void removeFirst() {
        if (head == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", 0));
        }
        head = head.next;
    }

    /**
     * 从索引位置删除
     *
     * @param index 索引
     * @throws IllegalArgumentException 找不到, 抛出 index 非法异常
     */
    public void remove(int index) throws IllegalArgumentException {
        if (index == 0) {
            removeFirst();
            return;
        }
        // 先找到插入索引的前一个位置
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        Node waitRemove = prev.next;
        if (waitRemove == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        prev.next = waitRemove.next;
    }

    private Node findNode(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next) {
            if (i == index) {
                return p;
            }
            i++;
        }
        return null;
    }

    private Node findLastNode() {
        Node curr = head;
        // 注意这里的终止条件是 curr.next == null
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }


    /**
     * Node 定义为内部类，是为了对外**隐藏**实现细节，没必要让类的使用者关心 Node 结构
     * 定义为 static 内部类，是因为 Node **不需要**与 SinglyLinkedList 实例相关，多个 SinglyLinkedList实例能共用 Node 类定义
     */
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private class NodeIterator implements Iterator<Integer> {
        Node temp = head;

        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Integer next() {
            int value = temp.value;
            temp = temp.next;
            return value;
        }
    }

}
