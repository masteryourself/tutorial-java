package org.masteryourself.tutorial.concurrent.v2.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : TestDeadLock
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/26 11:30
 */
@Slf4j
public class TestDeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                log.info("t1 获取到 lock1 锁");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    log.info("t1 获取到 lock2 锁");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock2) {
                log.info("t2 获取到 lock2 锁");
                synchronized (lock1) {
                    log.info("t2 获取到 lock1 锁");
                }
            }
        }, "t2").start();
    }

}
