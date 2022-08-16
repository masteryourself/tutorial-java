package org.masteryourself.tutorial.concurrent.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : SyncBiased3
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:21 PM
 */
@Slf4j
public class SyncBiased3 {

    // 需要添加 VM 参数禁用偏向锁 -XX:-UseBiasedLocking
    public static void main(String[] args) throws Exception {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        log.info("synchronized 前");
        // 0x0000000000000001 (non-biasable; age: 0) => 01=0000 0001(正常状态)
        System.out.println(classLayout.toPrintable());
        synchronized (d) {
            log.info("synchronized 中");
            // 0x000000030eb749e0 (thin lock: 0x000000030eb749e0) => 轻量级锁记录
            System.out.println(classLayout.toPrintable());
        }
        log.info("synchronized 后");
        //  0x0000000000000001 (non-biasable; age: 0) => 01=0000 0001(正常状态)
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
