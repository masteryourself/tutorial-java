package org.masteryourself.tutorial.jvm.jit;

import java.lang.reflect.Method;

/**
 * <p>description : ReflectDemo
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/8 11:01 AM
 */
public class ReflectDemo {

    public static void foo() {
        System.out.println("foo...");
    }

    public static void main(String[] args) throws Exception {
        Method foo = ReflectDemo.class.getMethod("foo");
        for (int i = 0; i <= 16; i++) {
            System.out.printf("%d\t", i);
            foo.invoke(null);
        }
    }

}
