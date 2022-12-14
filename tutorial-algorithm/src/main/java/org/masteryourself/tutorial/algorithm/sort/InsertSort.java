package org.masteryourself.tutorial.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : InsertSort
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/9 17:49
 */
@Slf4j
public class InsertSort {

    public static void insert(int[] array) {
        // i 表示要插入元素的索引
        for (int i = 1; i < array.length; i++) {
            // index 表示已排序好区域的索引
            int index = i - 1;
            // 表示待插入的值
            int insertValue = array[i];
            while (index >= 0) {
                if (insertValue < array[index]) {
                    array[index + 1] = array[index];
                } else {
                    break;
                }
                index--;
            }
            array[i] = insertValue;
            log.info("第 {} 轮插入排序, 排序结果是 {}", i, array);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 7, 4, 1, 3, 8, 9};
        insert(array);
    }

}
