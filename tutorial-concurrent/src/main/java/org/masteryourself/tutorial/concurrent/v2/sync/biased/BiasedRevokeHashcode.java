package org.masteryourself.tutorial.concurrent.v2.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : BiasedRevokeHashcode
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:21 PM
 */
@Slf4j
public class BiasedRevokeHashcode {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);
        // 0x0000000000000005 (biasable; age: 0) => 101(可偏向状态)
        System.out.println(classLayout.toPrintable());
        log.info("dog hashcode {}", d.hashCode());
        // 0x0000002aaf7cc201 (hash: 0x2aaf7cc2; age: 0) => 001(不可偏向状态)
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
