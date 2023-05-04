package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : SelectionSort
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/2 18:27
 */
@Slf4j
public class SelectionSort {

    private static void selection(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 从剩余数组中查找最小值
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // 找到最小值后与 i 发生交换
            swap(array, i, minIndex);
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
        selection(array);
    }

}
