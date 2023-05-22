package org.masteryourself.tutorial.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>description : AtomicStampedReferenceDemo
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/3 15:53
 */
@Slf4j
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) throws Exception {
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        log.info("版本 {}", stamp);
        // 如果中间有其它线程干扰，发生了 ABA 现象
        other();
        TimeUnit.SECONDS.sleep(2);
        // 尝试改为 C
        log.info("change A->C {}", ref.compareAndSet(prev, "C", stamp, stamp + 1));
    }

    private static void other() throws Exception {
        new Thread(() -> {
            log.info("change A->B {}", ref.compareAndSet(ref.getReference(), "B",
                    ref.getStamp(), ref.getStamp() + 1));
            log.info("更新版本为 {}", ref.getStamp());
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            log.info("change B->A {}", ref.compareAndSet(ref.getReference(), "A",
                    ref.getStamp(), ref.getStamp() + 1));
            log.info("更新版本为 {}", ref.getStamp());
        }, "t2").start();
    }

}
