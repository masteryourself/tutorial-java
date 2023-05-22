package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : 演示 字节码指令、操作数栈、常量池的关系
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 12:33 PM
 */
public class ByteCodeAnalysis {

    public static void main(String[] args) {
        int a = 10;
        int b = Short.MAX_VALUE + 1;
        int c = a + b;
        System.out.println(c);
    }

}
