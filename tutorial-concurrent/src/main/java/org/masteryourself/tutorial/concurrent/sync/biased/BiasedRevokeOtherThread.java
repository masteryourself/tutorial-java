package org.masteryourself.tutorial.concurrent.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : BiasedRevokeOtherThread
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/25 15:37
 */
@Slf4j
public class BiasedRevokeOtherThread {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();
        Thread t1 = new Thread(() -> {
            log.info("加锁前");
            // 0x0000000000000005 (biasable; age: 0) => 101(可偏向状态)
            log.info(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.info("加锁后");
                // 0x00007fa33411d005 (biased: 0x0000001fe8cd0474; epoch: 0; age: 0) => 101(线程 id + 偏向锁)
                log.info(ClassLayout.parseInstance(d).toPrintable());
            }
            log.info("解锁后");
            // 0x00007fa33411d005 (biased: 0x0000001fe8cd0474; epoch: 0; age: 0) => 101(线程 id + 偏向锁)
            log.info(ClassLayout.parseInstance(d).toPrintable());
            synchronized (BiasedRevokeOtherThread.class) {
                BiasedRevokeOtherThread.class.notify();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (BiasedRevokeOtherThread.class) {
                try {
                    BiasedRevokeOtherThread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加锁前");
            // 0x00007fa33411d005 (biased: 0x0000001fe8cd0474; epoch: 0; age: 0) => 101(线程 id + 偏向锁)
            log.info(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.info("加锁后");
                // 0x000000030e4699d8 (thin lock: 0x000000030e4699d8) => 00(锁记录地址 + 轻量级锁)
                log.info(ClassLayout.parseInstance(d).toPrintable());
            }
            log.info("解锁后");
            // 0x0000000000000001 (non-biasable; age: 0) => 001(不可偏向状态)
            log.info(ClassLayout.parseInstance(d).toPrintable());
        }, "t2");
        t2.start();
    }

    static class Dog {

    }

}
