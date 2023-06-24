package org.masteryourself.tutorial.concurrent.v2.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : InterruptSleepThread
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 11:08 AM
 */
@Slf4j
public class InterruptSleepThread {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // 打断状态是 false
                log.info("打断状态是 {}", Thread.currentThread().isInterrupted());
                log.error(e.getMessage(), e);
            }
        }, "t1");
        t1.start();
        // 确保线程处于 sleep 状态
        TimeUnit.MILLISECONDS.sleep(500);
        // 对于阻塞线程, 会清除打断标记
        t1.interrupt();
        // t1 打断状态是 false
        log.info("t1 打断状态是 {}", t1.isInterrupted());
    }

}
