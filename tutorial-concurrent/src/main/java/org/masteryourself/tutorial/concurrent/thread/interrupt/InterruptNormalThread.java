package org.masteryourself.tutorial.concurrent.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : InterruptNormalThread
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 11:19 AM
 */
@Slf4j
public class InterruptNormalThread {

    public static void main(String[] args) throws Exception {
        Thread t2 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if (interrupted) {
                    // 打断状态是: true
                    log.info("打断状态是: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        // 对于正常线程, 不会清除打断标记
        t2.interrupt();
        // t2 打断状态是 true
        log.info("t2 打断状态是 {}", t2.isInterrupted());
    }

}
