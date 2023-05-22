package org.masteryourself.tutorial.concurrent.sync.revoke;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : RevokeByHashcode
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 2:07 PM
 */
@Slf4j
public class RevokeByThread {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();

        Thread t1 = new Thread(() -> {
            synchronized (d) {
                // 0x00007fa841889005 (biased: 0x0000001fea106224; epoch: 0; age: 0) => 偏向锁
                log.info(ClassLayout.parseInstance(d).toPrintable());
            }
            synchronized (RevokeByThread.class) {
                RevokeByThread.class.notify();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (RevokeByThread.class) {
                try {
                    RevokeByThread.class.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            // 0x00007fa841889005 (biased: 0x0000001fea106224; epoch: 0; age: 0) => 偏向锁
            log.info(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                // 0x0000000306ab99d8 (thin lock: 0x0000000306ab99d8) => 升级为轻量级锁
                log.info(ClassLayout.parseInstance(d).toPrintable());
            }
            // 0x0000000000000001 (non-biasable; age: 0) => 正常状态
            log.info(ClassLayout.parseInstance(d).toPrintable());
        }, "t2");
        t2.start();

    }

    static class Dog {

    }

}
