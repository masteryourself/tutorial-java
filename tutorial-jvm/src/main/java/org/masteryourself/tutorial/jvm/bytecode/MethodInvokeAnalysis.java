package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : 方法调用字节码分析
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 2:05 PM
 */
public class MethodInvokeAnalysis {

    public MethodInvokeAnalysis() {
    }

    private void test1() {
    }

    private final void test2() {
    }

    public void test3() {
    }

    public static void test4() {
    }

    public static void main(String[] args) {
        MethodInvokeAnalysis d = new MethodInvokeAnalysis();
        d.test1();
        d.test2();
        d.test3();
        d.test4();
        MethodInvokeAnalysis.test4();
    }

}
