package org.masteryourself.tutorial.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : SyncBiased2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:14 PM
 */
@Slf4j
public class SyncBiased2 {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        log.info("synchronized 前");
        // 0x0000000000000005 (biasable; age: 0) => 05=0000 0101(此时具有偏向锁)
        System.out.println(classLayout.toPrintable());
        synchronized (d) {
            log.info("synchronized 中");
            // 0x00007fc579af5005 (biased: 0x0000001ff15e6bd4; epoch: 0; age: 0) => 线程 id + 偏向锁
            System.out.println(classLayout.toPrintable());
        }
        log.info("synchronized 后");
        //  0x00007fc579af5005 (biased: 0x0000001ff15e6bd4; epoch: 0; age: 0) => 线程 id + 偏向锁
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
