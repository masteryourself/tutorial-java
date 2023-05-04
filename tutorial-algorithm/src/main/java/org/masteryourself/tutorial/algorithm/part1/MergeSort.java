package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : MergeSort
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/4 17:35
 */
@Slf4j
public class MergeSort {

    public static void process(int[] array, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) / 2;
        process(array, L, mid);
        process(array, mid + 1, R);
        // 归并排序结果
        merge(array, L, mid, R);
    }

    private static void merge(int[] array, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int pl = L;
        int pr = M + 1;
        int index = 0;
        while (pl <= M && pr <= R) {
            help[index++] = array[pl] <= array[pr] ? array[pl++] : array[pr++];
        }
        while (pl <= M) {
            help[index++] = array[pl++];
        }
        while (pr <= R) {
            help[index++] = array[pr++];
        }
        for (int i = 0; i < help.length; i++) {
            array[L + i] = help[i];
        }
        log.info("当前排序数组是 {}", array);
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 7, 4, 1, 3, 8, 9};
        process(array, 0, array.length - 1);
    }

}
