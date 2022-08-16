package org.masteryourself.tutorial.concurrent.sync.revoke;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : RevokeByHashcode
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 2:07 PM
 */
@Slf4j
public class RevokeByWaitNotify {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();

        Thread t1 = new Thread(() -> {
            // 0x0000000000000005 (biasable; age: 0) => 偏向锁
            log.info(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                // 0x00007fcb45167005 (biased: 0x0000001ff2d1459c; epoch: 0; age: 0) => 偏向锁 + 线程 ID
                log.info(ClassLayout.parseInstance(d).toPrintable());
                try {
                    d.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                // 0x00007fcb460198ca (fat lock: 0x00007fcb460198ca) => 重量级锁
                log.info(ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t1");
        t1.start();

        new Thread(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            synchronized (d) {
                log.debug("notify");
                d.notify();
            }
        }, "t2").start();
    }

    static class Dog {

    }

}
