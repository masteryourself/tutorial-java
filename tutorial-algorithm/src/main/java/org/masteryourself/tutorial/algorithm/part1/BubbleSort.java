package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : BubbleSort
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/3 11:45
 */
@Slf4j
public class BubbleSort {

    private static void bubble(int[] array) {
        // 3. 一共需要交换 array.length - 1 轮
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1 - j; i++) {
                // 1. 每一轮冒泡是为了找出最大值, 放到最右边
                if (array[i] > array[i + 1]) {
                    // 2. 将最大值放到最右边, 标记发生了排序交换
                    swap(array, i, i + 1);
                }
            }
            log.info("当前排序数组是 {}", array);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 7, 4, 1, 3, 8, 9};
        bubble(array);
    }

}
