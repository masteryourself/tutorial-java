package org.masteryourself.tutorial.jvm.stack;

/**
 * <p>description : 栈内存溢出问题演示
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 2:47 PM
 */
public class StackOverflow {

    private static int count = 1;

    /**
     * 添加 VM 参数 -Xss256k
     */
    public static void main(String[] args) {
        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void method1() {
        count++;
        method1();
    }

}
