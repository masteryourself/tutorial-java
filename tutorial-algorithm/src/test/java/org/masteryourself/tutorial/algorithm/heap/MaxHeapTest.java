package org.masteryourself.tutorial.algorithm.heap;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : MaxHeapTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 14:06
 */
class MaxHeapTest {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(Arrays.toString(heap.array));
        System.out.println(heap.poll());
        heap.offer(100);
        System.out.println(Arrays.toString(heap.array));
    }

}