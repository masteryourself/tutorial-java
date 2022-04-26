package org.masteryourself.tutorial.sync.revoke;

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
public class RevokeByHashcode {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        log.info("dog hashcode {}", dog.hashCode());
        // 0x00000031221be201 (hash: 0x31221be2; age: 0) => 此时没使用偏向锁, 否则 hashcode 无法存储
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

    static class Dog {

    }

}
