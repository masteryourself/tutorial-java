package org.masteryourself.tutorial.concurrent.thread.daemon;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : DaemonThread
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 3:00 PM
 */
@Slf4j
public class DaemonThread {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            log.info("子线程运行开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.info("子线程运行结束");
        }, "t1");
        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("主线程运行完成");
    }

}
