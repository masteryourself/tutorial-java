package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : 条件判断字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 1:35 PM
 */
public class ConditionAnalysis {

    public static void main(String[] args) {
        int a = 0;
        if (a == 0) {
            a = 10;
        } else {
            a = 20;
        }
    }

}
