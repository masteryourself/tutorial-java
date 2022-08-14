package org.masteryourself.tutorial.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>description : ReentrantLockDemo4
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 20:27
 */
@Slf4j
public class ReentrantLockDemo4 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        // 等烟休息室
        Condition waitCigaretteCondition = lock.newCondition();
        // 等外卖休息室
        Condition waitBreakfastCondition = lock.newCondition();
        final boolean[] hasCigarette = {false};
        final boolean[] hasBreakfast = {false};

        new Thread(() -> {
            lock.lock();
            try {
                while (!hasCigarette[0]) {
                    try {
                        waitCigaretteCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("烟送来了, 干活");
            } finally {
                lock.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            lock.lock();
            try {
                while (!hasBreakfast[0]) {
                    try {
                        waitBreakfastCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("饭送来了, 干活");
            } finally {
                lock.unlock();
            }
        }, "小女").start();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                hasCigarette[0] = true;
                log.info("烟送过去了...打电话通知客户");
                waitCigaretteCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "外卖小哥").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                hasBreakfast[0] = true;
                log.info("饭送过去了...打电话通知客户");
                waitBreakfastCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "跑腿小哥").start();

    }

}
