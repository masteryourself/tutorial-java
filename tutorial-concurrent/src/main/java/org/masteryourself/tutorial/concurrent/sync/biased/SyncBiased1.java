package org.masteryourself.tutorial.concurrent.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : SyncBiased1
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 12:50 PM
 */
@Slf4j
public class SyncBiased1 {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        // 0x0000000000000005 (biasable; age: 0) => 05=0000 0101(此时具有偏向锁)
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

    static class Dog {

    }

}
