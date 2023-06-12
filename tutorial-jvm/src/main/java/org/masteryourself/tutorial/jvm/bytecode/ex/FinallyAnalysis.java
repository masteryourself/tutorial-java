package org.masteryourself.tutorial.jvm.bytecode.ex;

/**
 * <p>description : finally 字节码分析
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 2:34 PM
 */
public class FinallyAnalysis {

    public static void main(String[] args) {
        int i = 0;
        try {
            i = 10;
        } catch (Exception e) {
            i = 20;
        } finally {
            i = 30;
        }
    }

}
