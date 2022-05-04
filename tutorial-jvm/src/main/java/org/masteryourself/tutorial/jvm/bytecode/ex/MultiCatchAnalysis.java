package org.masteryourself.tutorial.jvm.bytecode.ex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>description : 多 catch 字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 2:33 PM
 */
public class MultiCatchAnalysis {

    public static void main(String[] args) {
        try {
            Method test = MultiCatchAnalysis.class.getMethod("test");
            test.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        System.out.println("ok");
    }

}
