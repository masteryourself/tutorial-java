package org.masteryourself.tutorial.concurrent.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>description : ReentrantLockDemo3
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 20:27
 */
@Slf4j
public class ReentrantLockDemo3 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.info("获取锁超时");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("获取锁超时");
                return;
            }
            try {
                log.info("获取到锁, 执行业务逻辑");
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        // 主线程获取到锁
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
