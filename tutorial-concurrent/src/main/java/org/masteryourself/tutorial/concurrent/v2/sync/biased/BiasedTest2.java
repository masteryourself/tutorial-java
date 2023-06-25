package org.masteryourself.tutorial.concurrent.v2.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : BiasedTest2
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:14 PM
 */
@Slf4j
public class BiasedTest2 {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        log.info("加锁前");
        // 0x0000000000000005 (biasable; age: 0) => 101(可偏向状态)
        System.out.println(classLayout.toPrintable());
        synchronized (d) {
            log.info("加锁后");
            // 0x00007fd551811005 (biased: 0x0000001ff5546044; epoch: 0; age: 0) => 101(线程 id + 偏向锁)
            System.out.println(classLayout.toPrintable());
        }
        log.info("解锁后");
        //  0x00007fd551811005 (biased: 0x0000001ff5546044; epoch: 0; age: 0) => 101(线程 id + 偏向锁)
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
