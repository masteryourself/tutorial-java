package org.masteryourself.tutorial.concurrent.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : BiasedTest1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 12:50 PM
 */
@Slf4j
public class BiasedTest1 {

    // 需要添加 VM 参数禁用延迟 -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        // 不禁用延迟：0x0000000000000001 (biasable; age: 0) => 001(不可偏向状态)
        // 禁用延迟：  0x0000000000000005 (biasable; age: 0) => 101(可偏向状态)
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

    static class Dog {

    }

}
