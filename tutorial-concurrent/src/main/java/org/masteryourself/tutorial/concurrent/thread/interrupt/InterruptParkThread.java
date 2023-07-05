package org.masteryourself.tutorial.concurrent.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>description : InterruptParkThread
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 2:43 PM
 */
@Slf4j
public class InterruptParkThread {

    public static void main(String[] args) throws Exception {
        Thread t3 = new Thread(() -> {
            LockSupport.park();
            // 打断状态 true
            log.info("打断状态 {}", Thread.currentThread().isInterrupted());
            // 此时再想使用 park() 方法会失效, 除非将打断标记重置
            LockSupport.park();
            log.info("程序继续运行");
        }, "t3");
        t3.start();
        TimeUnit.MILLISECONDS.sleep(100);
        // 对于 park 线程, 不会清除打断标记
        t3.interrupt();
    }

}


