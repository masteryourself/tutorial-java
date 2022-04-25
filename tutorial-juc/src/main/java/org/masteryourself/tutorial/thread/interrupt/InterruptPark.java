package org.masteryourself.tutorial.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>description : InterruptPark
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 2:43 PM
 */
@Slf4j
public class InterruptPark {

    public static void main(String[] args) throws Exception {
        Thread t3 = new Thread(() -> {
            LockSupport.park();
            // 打断状态 true
            log.info("打断状态 {}", Thread.currentThread().isInterrupted());
        }, "t1");
        t3.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.interrupt();
    }

}
