package org.masteryourself.tutorial.algorithm.array;

import org.springframework.util.StopWatch;

/**
 * <p>description : ArrayCacheLineTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/11 15:11
 */
public class ArrayCacheLine {

    public static void main(String[] args) {
        int rows = 1000000;
        int columns = 14;
        int[][] a = new int[rows][columns];

        StopWatch sw = new StopWatch();
        sw.start("ij");
        ij(a, rows, columns);
        sw.stop();
        sw.start("ji");
        ji(a, rows, columns);
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    public static void ij(int[][] a, int rows, int columns) {
        long sum = 0L;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += a[i][j];
            }
        }
    }

    public static void ji(int[][] a, int rows, int columns) {
        long sum = 0L;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                sum += a[i][j];
            }
        }
    }

}
