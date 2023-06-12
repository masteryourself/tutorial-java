package org.masteryourself.tutorial.concurrent.stack;

/**
 * <p>description : StackFrame
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/12 17:43
 */
public class StackFrame {

    public static void main(String[] args) {
        method1(10);
    }

    public static void method1(int x) {
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    public static Object method2() {
        Object n = new Object();
        return n;
    }

}
