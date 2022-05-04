package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : i++ 字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 1:16 PM
 */
public class IncAnalysis {

    public static void main(String[] args) {
        int a = 10;
        int b = a++ + ++a + a--;
        // 11
        System.out.println(a);
        // 34
        System.out.println(b);
    }

}
