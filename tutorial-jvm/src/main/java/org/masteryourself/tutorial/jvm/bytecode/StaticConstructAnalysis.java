package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : cinit 构造方法字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 1:54 PM
 */
public class StaticConstructAnalysis {

    static int i = 10;

    static {
        i = 20;
    }

    static {
        i = 30;
    }

}
