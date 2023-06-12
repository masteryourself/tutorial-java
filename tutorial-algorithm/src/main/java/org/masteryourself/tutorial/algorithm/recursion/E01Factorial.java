package org.masteryourself.tutorial.algorithm.recursion;

/**
 * <p>description : E01Factorial
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 13:19
 */
public class E01Factorial {

    public static void main(String[] args) {
        System.out.println(f(10));
    }

    public static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return n * f(n - 1);
    }

}
