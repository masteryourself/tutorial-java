package org.masteryourself.tutorial.jvm.stack;

/**
 * <p>description : 栈内存溢出案例
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 2:47 PM
 */
public class StackOverflowDemo {

    /**
     * 添加 VM 参数 -Xss256k
     */
    public static void main(String[] args) {
        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void method1() {
        method1();
    }

}
