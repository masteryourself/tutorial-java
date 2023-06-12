package org.masteryourself.tutorial.algorithm.heap;

/**
 * <p>description : MaxHeap
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 13:53
 */
public class MaxHeap {

    int[] array;
    int size;

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {
        return array[0];
    }

    /**
     * 删除堆顶元素
     * 1. 先查找堆顶元素
     * 2. 将堆顶元素与最后一个元素交换位置, 然后使 size--
     * 3. 交换后的堆顶元素执行下潜
     *
     * @return 堆顶元素
     */
    public int poll() {
        int value = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return value;
    }

    /**
     * 删除指定索引处元素
     * 1. 先查找指定索引的元素
     * 2. 将指定索引的元素与最后一个元素交换位置, 然后使 size--
     * 3. 交换后的指定索引元素执行下潜
     *
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    /**
     * 建堆
     * 1. 找到最后一个非叶子结点
     * 2. 从后向前, 对每个结点执行下潜
     * 下潜
     * 1. 从堆顶开始, 将父元素和两个孩子较大者交换
     * 2. 直到父元素大于两个孩子, 或没有孩子为止
     */
    private void heapify() {
        int index = this.size / 2 - 1;
        for (int i = index; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
     *
     * @param offered
     */
    private void up(int offered) {
        // 当前子元素索引, 因为 size 还没有执行 ++
        int childIndex = size;
        // 当前元素的父元素索引
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;
            if (offered > array[parentIndex]) {
                array[childIndex] = array[parentIndex];
            } else {
                break;
            }
            childIndex = parentIndex;
        }
        array[childIndex] = offered;
    }

    /**
     * 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它大
     *
     * @param parentIndex 父元素索引
     */
    private void down(int parentIndex) {
        int leftChildIndex = parentIndex * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;
        int maxIndex = parentIndex;
        if (leftChildIndex < size && array[leftChildIndex] > array[maxIndex]) {
            maxIndex = leftChildIndex;
        }
        if (rightChildIndex < size && array[rightChildIndex] > array[maxIndex]) {
            maxIndex = rightChildIndex;
        }
        // 如果两者不相等, 说明 parentIndex 需要调换位置, 并且调换后的位置需要下潜
        if (maxIndex != parentIndex) {
            swap(parentIndex, maxIndex);
            down(maxIndex);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
