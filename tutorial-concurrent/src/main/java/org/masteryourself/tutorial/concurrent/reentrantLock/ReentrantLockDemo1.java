package org.masteryourself.tutorial.concurrent.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>description : ReentrantLockDemo1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 20:27
 */
@Slf4j
public class ReentrantLockDemo1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        lock.lock();
        try {
            log.info("execute method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    private static void method2() {
        lock.lock();
        try {
            log.info("execute method2");
        } finally {
            lock.unlock();
        }
    }

}
