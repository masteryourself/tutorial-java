package org.masteryourself.tutorial.concurrent.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>description : ReentrantLockDemo2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 20:27
 */
@Slf4j
public class ReentrantLockDemo2 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                lock.lockInterruptibly();
                try {
                    log.info("获取到锁, 执行业务逻辑");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                log.warn("获取锁过程中被打断, 跳过业务逻辑执行");
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        // 主线程获取到锁
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            // 打断 t1 线程
            t1.interrupt();
            log.info("主线程执行打断逻辑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
