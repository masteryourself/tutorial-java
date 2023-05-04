package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : InsertSort
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/4 16:28
 */
@Slf4j
public class InsertSort {

    public static void insert(int[] array) {
        // i 表示要插入元素的索引
        for (int i = 1; i < array.length; i++) {
            // index 表示已排序好区域的索引
            int index = i - 1;
            int insertValue = array[i];
            // 使其在 0~i 位置上有序
            for (int j = index; j >= 0; j--) {
                if (array[j] > insertValue) {
                    swap(array, j, j + 1);
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
        insert(array);
    }

}
