package org.masteryourself.tutorial.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : MyLockTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/30 13:11
 */
@Slf4j
public class MyLockTest {

    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        new Thread(() -> {
            myLock.lock();
            try {
                log.info("获取锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                log.info("释放锁");
                myLock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            myLock.lock();
            try {
                log.info("获取锁");
            } finally {
                log.info("释放锁");
                myLock.unlock();
            }
        }, "t2").start();
    }

}
