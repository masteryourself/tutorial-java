package org.masteryourself.tutorial.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : BubbleEnhanceSort, 冒泡排序增强
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/9 15:54
 */
@Slf4j
public class BubbleEnhanceSort {

    public static void bubbleEnhance(int[] array) {
        AtomicInteger cycleCount = new AtomicInteger();
        // 记录上一次冒泡的交换索引
        int nextCycleCount = array.length - 1;
        // 一共需要交换 array.length - 1 轮
        while (true) {
            // 每一轮冒泡是为了找出最大值, 放到最右边
            int n = 0;
            for (int i = 0; i < nextCycleCount; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    n = i;
                }
            }
            nextCycleCount = n;
            log.info("第 {} 轮冒泡, 循环次数是 {}, 冒泡结果是 {}", cycleCount.incrementAndGet(), nextCycleCount, array);
            // n=0 表示上轮排序已经没有发生交换, 不再需要冒泡了
            if (n == 0) {
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
        bubbleEnhance(array);
    }

}
