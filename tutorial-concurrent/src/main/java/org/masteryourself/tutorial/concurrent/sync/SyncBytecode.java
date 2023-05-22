package org.masteryourself.tutorial.concurrent.sync;

/**
 * <p>description : SyncBytecode
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 11:32 AM
 */
public class SyncBytecode {

    static final Object lock = new Object();
    static int counter = 0;

    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }

}
