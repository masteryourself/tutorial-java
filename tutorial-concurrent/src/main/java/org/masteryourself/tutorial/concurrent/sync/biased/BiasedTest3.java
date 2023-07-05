package org.masteryourself.tutorial.concurrent.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : BiasedTest3
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:21 PM
 */
@Slf4j
public class BiasedTest3 {

    // 需要添加 VM 参数禁用偏向锁 -XX:-UseBiasedLocking
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        log.info("加锁前");
        // 0x0000000000000001 (non-biasable; age: 0) => 001(不可偏向状态)
        System.out.println(classLayout.toPrintable());
        synchronized (d) {
            log.info("加锁后");
            // 0x000000030eb749e0 (thin lock: 0x000000030eb749e0) => 00(锁记录地址 + 轻量级锁)
            System.out.println(classLayout.toPrintable());
        }
        log.info("解锁后");
        //  0x0000000000000001 (non-biasable; age: 0) => 001(不可偏向状态)
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
