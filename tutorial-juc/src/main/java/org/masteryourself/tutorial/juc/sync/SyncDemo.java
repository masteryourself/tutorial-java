package org.masteryourself.tutorial.juc.sync;

/**
 * <p>description : SyncDemo
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/23 7:14 AM
 */
public class SyncDemo {

    private Object obj = new Object();

    public void m1() {
        synchronized (obj) {
        }
    }

    public synchronized void m2() {
    }

    public static synchronized void m3() {
    }

}
