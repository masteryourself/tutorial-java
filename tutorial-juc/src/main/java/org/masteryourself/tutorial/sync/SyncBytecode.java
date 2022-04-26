package org.masteryourself.tutorial.sync;

/**
 * <p>description : SyncBytecode
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 11:32 AM
 */
public class SyncBytecode {

    static final Object obj = new Object();

    public static void method1() {
        synchronized( obj ) {
            // 同步块 A
            method2();
        }
    }

    public static void method2() {
        synchronized( obj ) {
            // 同步块 B
        }
    }

}
