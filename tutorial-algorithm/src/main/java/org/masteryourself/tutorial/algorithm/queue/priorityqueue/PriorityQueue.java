package org.masteryourself.tutorial.algorithm.queue.priorityqueue;

import org.masteryourself.tutorial.algorithm.queue.queue.Queue;

/**
 * <p>description : PriorityQueue
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 20:15
 */
public class PriorityQueue<E extends Priority> implements Queue<E> {

    /**
     * 用数组实现优先级队列
     * 采用大顶堆实现（父节点 > 左右孩子节点）
     */
    private Priority[] array;
    private int size;

    public PriorityQueue(int capacity) {
        this.array = new Priority[capacity];
    }

    /**
     * 1. 入堆新元素, 加入到数组末尾(索引位置 child)
     * 2. 不断比较新元素与它父节点(parent)优先级
     * - 如果父节点优先级低, 则向下移动, 并找到下一个 parent
     * - 直至父节点优先级更高或 child==0 为止
     *
     * @param offered 待插入值
     * @return
     */
    @Override
    public boolean offer(E offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }

    /**
     * 1. 交换堆顶和尾部元素, 因为尾部元素出队非常快捷
     * 2. 下潜
     * - 从堆顶开始, 将父元素和两个孩子较大者交换
     * - 直到父元素大于两个孩子, 或没有孩子为止
     *
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        array[size] = null;

        shiftDown(0);
        return (E) e;
    }

    private void shiftDown(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            shiftDown(max);
        }
    }

    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

}
