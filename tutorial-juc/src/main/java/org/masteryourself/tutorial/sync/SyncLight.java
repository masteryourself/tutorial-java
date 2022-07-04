package org.masteryourself.tutorial.sync;

/**
 * <p>description : SyncLight
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/7/4 19:18
 */
public class SyncLight {

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
