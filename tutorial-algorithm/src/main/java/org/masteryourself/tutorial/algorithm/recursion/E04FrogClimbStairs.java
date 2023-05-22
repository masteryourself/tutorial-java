package org.masteryourself.tutorial.algorithm.recursion;

/**
 * <p>description : FrogClimbStairs
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 13:49
 */
public class E04FrogClimbStairs {

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

}
