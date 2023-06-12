package org.masteryourself.tutorial.algorithm.binarysearch;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : BinarySearch
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/9 11:34
 */
@Slf4j
public class BinarySearch {

    /**
     * 1. i, j, m 指针都可能是查找目标
     * 2. 因为 1. i > j 时表示区域内没有要找的了
     * 3. 每次改变 i, j 边界时, m 已经比较过不是目标, 因此分别 m+1 m-1
     * 4. 向左查找, 比较次数少, 向右查找, 比较次数多
     *
     * @param array  待查找的升序数组
     * @param target 待查找的目标值
     * @return 找到则返回索引, 找不到返回 -1
     */
    public static int binarySearchBasic(int[] array, int target) {
        int i = 0, j = array.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < array[m]) {            // 在左边
                j = m - 1;
            } else if (array[m] < target) {        // 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 1. i, m 指针可能是查找目标
     * 2. j 指针不可能是查找目标
     * 3. 因为 1. 2. i >= j 时表示区域内没有要找的了
     * 4. 改变 i 边界时, m 已经比较过不是目标, 因此需要 i=m+1
     * 5. 改变 j 边界时, m 已经比较过不是目标, 同时因为 2. 所以 j=m
     *
     * @param array  待查找的升序数组
     * @param target 待查找的目标值
     * @return 找到则返回索引, 找不到返回 -1
     */
    public static int binarySearchAlternative(int[] array, int target) {
        int i = 0, j = array.length;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < array[m]) {            // 在左边
                j = m;
            } else if (array[m] < target) {        // 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int binarySearchBalance(int[] array, int target) {
        int i = 0;
        int j = array.length;
        while (i < j - 1) {
            int m = (i + j) >>> 1;
            if (target < array[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        return array[i] == target ? i : -1;
    }

    /**
     * 对于数组 [1, 2, 3, 4, 4, 5, 6, 7]，查找元素 4，结果是索引 3
     */
    public static int binarySearchLeftmost(int[] array, int target) {
        int i = 0, j = array.length - 1;
        int candidateIndex = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < array[m]) {
                j = m - 1;
            } else if (target > array[m]) {
                i = m + 1;
            } else {
                candidateIndex = m;
                j = m - 1;
            }
        }
        return candidateIndex;
    }

    /**
     * 对于数组 [1, 2, 3, 4, 4, 5, 6, 7]，查找元素 4，结果是索引 4
     */
    public static int binarySearchRightmost(int[] array, int target) {
        int i = 0, j = array.length - 1;
        int candidateIndex = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < array[m]) {
                j = m - 1;
            } else if (target > array[m]) {
                i = m + 1;
            } else {
                candidateIndex = m;
                i = m + 1;
            }
        }
        return candidateIndex;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 5, 6, 7};
        int target = 4;
        // int idx = binarySearchBasic(array, target);
        // int idx = binarySearchAlternative(array, target);
        // int idx = binarySearchBalance(array, target);
        // int idx = binarySearchLeftmost(array, target);
        int idx = binarySearchRightmost(array, target);
        log.info("查找到的索引位置是 {}", idx);
    }

}
