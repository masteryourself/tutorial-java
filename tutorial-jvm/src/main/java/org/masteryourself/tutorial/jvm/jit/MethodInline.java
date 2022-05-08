package org.masteryourself.tutorial.jvm.jit;

/**
 * <p>description : 方法内联
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/8 10:55 AM
 */
public class MethodInline {

    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 500; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                x = square(9);
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\t%d\n", i, x, (end - start));
        }
    }

    private static int square(final int i) {
        return i * i;
    }

}
