package org.masteryourself.tutorial.juc.interrupt;

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
public class InterruptPark2 {

    public static void main(String[] args) throws Exception {
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                // 如果线程已经被打断, 这里的 park() 方法会失效，会一直循环 3 次
                LockSupport.park();
                // 打断状态 true
                log.info("打断状态 {}", Thread.currentThread().isInterrupted());
            }
        }, "t1");
        t3.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.interrupt();
    }

}
