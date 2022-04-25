package org.masteryourself.tutorial.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : InterruptSleep
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 11:08 AM
 */
@Slf4j
public class InterruptSleep {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        // t1 interrupt 状态是 false
        log.info("t1 interrupt 状态是 {}", t1.isInterrupted());
        t1.interrupt();
        // t1 interrupt 状态是 true
        log.info("t1 interrupt 状态是 {}", t1.isInterrupted());
    }

}
