package org.masteryourself.tutorial.algorithm.recursion;

import java.util.Arrays;

/**
 * <p>description : E05FibonacciEnhance
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 14:01
 */
public class E05FibonacciEnhance {

    public static void main(String[] args) {
        int n = 10;
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        System.out.println(fibonacci(cache, n));
    }

    public static int fibonacci(int[] cache, int n) {
        if (cache[n] != -1) {
            return cache[n];
        }
        int value = fibonacci(cache, n - 1) + fibonacci(cache, n - 2);
        cache[n] = value;
        return value;
    }

}
