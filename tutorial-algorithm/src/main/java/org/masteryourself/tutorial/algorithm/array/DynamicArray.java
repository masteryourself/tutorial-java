package org.masteryourself.tutorial.algorithm.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * <p>description : DynamicArray
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/11 14:09
 */
public class DynamicArray implements Iterable<Integer> {

    // 逻辑大小
    private int size = 0;

    // 容量
    private int capacity = 10;

    // 存储元素的数组
    private int[] array = {};

    /**
     * 向最后位置 [size] 添加元素
     *
     * @param element 待添加元素
     */
    public void add(int element) {
        add(size, element);
    }

    /**
     * 向 [0 .. size] 位置添加元素
     *
     * @param index   索引位置
     * @param element 待添加元素
     */
    public void add(int index, int element) {
        // 1. 校验是否需要扩容
        checkAndGrow();
        // 2. 移动数组
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        // 3. 赋值
        array[index] = element;
        size++;
    }

    /**
     * 从 [0 .. size) 范围删除元素
     *
     * @param index 索引位置
     * @return 被删除元素
     */
    public int remove(int index) {
        // 1. 先获取值
        int value = array[index];
        // 2. 移动数组
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return value;
    }

    /**
     * 查询元素
     *
     * @param index 索引位置, 在 [0..size) 区间内
     * @return 该索引位置的元素
     */
    public int get(int index) {
        return array[index];
    }

    /**
     * 遍历方法1
     *
     * @param consumer 遍历要执行的操作, 入参: 每个元素
     */
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);
        }
    }

    /**
     * 遍历方法2 - 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                // 有没有下一个元素
                return index < size;
            }

            @Override
            public Integer next() {
                // 返回当前元素,并移动到下一个元素
                return array[index++];
            }
        };
    }

    /**
     * 遍历方法3 - stream 遍历
     *
     * @return stream 流
     */
    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

    private void checkAndGrow() {
        // 校验是否初始化
        if (size == 0) {
            array = new int[capacity];
        }
        // 校验是否需要扩容
        if (size == capacity) {
            // 按照 1.5 倍扩容
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

}
