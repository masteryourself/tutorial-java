package org.masteryourself.tutorial.concurrent.sync.order;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : FixedOrderUseWait
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 11:57
 */
@Slf4j
public class FixedOrderUseWait {

    private static final Object lock = new Object();
    private static volatile boolean printFlag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!printFlag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            log.info("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("2");
            synchronized (lock) {
                printFlag = true;
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
