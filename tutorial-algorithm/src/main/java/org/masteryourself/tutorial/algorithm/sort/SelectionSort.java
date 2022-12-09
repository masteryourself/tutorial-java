package org.masteryourself.tutorial.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : SelectionSort, 选择排序
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/9 16:26
 */
@Slf4j
public class SelectionSort {

    public static void selection(int[] array) {
        // 3. 一直循环到所有数据都是已排序
        for (int j = 0; j < array.length - 1; j++) {
            // 1. 查找未排序区域中的最小值, minIndex 表示最小值的索引
            int minIndex = j;
            for (int i = j + 1; i < array.length; i++) {
                if (array[minIndex] > array[i]) {
                    minIndex = i;
                }
            }
            // 2. 将最小值放到已排序好的区域中
            if (minIndex != j) {
                swap(array, j, minIndex);
                log.info("第 {} 轮选择排序, 排序结果是 {}", j + 1, array);
            }
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
