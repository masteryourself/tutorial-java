package org.masteryourself.tutorial.sync.biased;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>description : SyncHashcode
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/26 1:21 PM
 */
@Slf4j
public class SyncHashcode {

    public static void main(String[] args) throws Exception {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);
        // 0x0000000000000001 (non-biasable; age: 0), 此时 hashcode 没有值
        System.out.println(classLayout.toPrintable());
        log.info("dog hashcode {}", d.hashCode());
        // 0x0000001888ff2c01 (hash: 0x1888ff2c; age: 0), 此时 hashcode 有值
        System.out.println(classLayout.toPrintable());
    }

    static class Dog {

    }

}
