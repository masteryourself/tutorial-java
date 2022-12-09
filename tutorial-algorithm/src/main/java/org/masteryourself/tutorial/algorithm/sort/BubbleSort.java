package org.masteryourself.tutorial.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : BubbleSort, 冒泡排序
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/9 14:41
 */
@Slf4j
public class BubbleSort {

    public static void bubble(int[] array) {
        // 3. 一共需要交换 array.length - 1 轮
        for (int j = 0; j < array.length - 1; j++) {
            boolean swap = false;
            // 1. 每一轮冒泡是为了找出最大值, 放到最右边
            for (int i = 0; i < array.length - 1 - j; i++) {
                if (array[i] > array[i + 1]) {
                    // 2. 将最大值放到最右边, 标记发生了排序交换
                    swap(array, i, i + 1);
                    swap = true;
                }
            }
            log.info("第 {} 轮冒泡, 循环次数是 {}, 冒泡结果是 {}", j + 1, array.length - 1 - j, array);
            // 如果一次交换都没有发生, 表示数组已经有序了, 不需要再排序了
            if (!swap) {
                log.info("数组已经有序了, 不需要再冒泡了");
                break;
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
        bubble(array);
    }

}
