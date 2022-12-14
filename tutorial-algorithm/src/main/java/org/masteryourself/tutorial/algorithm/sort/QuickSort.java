package org.masteryourself.tutorial.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : QuickSort, 快速排序
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/13 17:13
 */
@Slf4j
public class QuickSort {

    public static void quick(int[] array, int l, int h) {
        // 这个表示区间内没有元素, 结束循环
        if (l >= h) {
            return;
        }
        // 索引值
        int sortedIndex = partition(array, l, h);
        // 左边分区范围
        quick(array, l, sortedIndex - 1);
        // 右边分区范围
        quick(array, sortedIndex + 1, h);
    }

    public static int partition(int[] array, int l, int h) {
        // 基准点元素
        int pv = array[h];
        // i 是左边界, 从第一个元素开始
        int i = l;
        for (int j = l; j < h; j++) {
            if (array[j] < pv) {
                // 交换 i 和 j 的位置, 确保比基准点小的元素在左侧
                if (i != h) {
                    swap(array, i, j);
                }
                i++;
            }
        }
        // 交换基准点
        if (i != h) {
            swap(array, i, h);
        }
        log.info("快速排序, i={} , 排序结果是 {}", i, array);
        // 返回值代表了基准点元素的正确索引, 用它来确定下一轮分区的边界
        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 7, 2, 9, 8, 1, 4};
        quick(array, 0, array.length - 1);
    }

}
