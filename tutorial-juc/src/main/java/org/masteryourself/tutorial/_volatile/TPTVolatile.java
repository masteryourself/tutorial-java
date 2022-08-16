package org.masteryourself.tutorial._volatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : TPTVolatile
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/15 19:30
 */
@Slf4j
public class TPTVolatile {

    private static  boolean stop = false;

    public static void main(String[] args) throws Exception {
        Thread monitorThread = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.info("料理后事 ...");
                    break;
                }
                try {
                    // 情况一: 如果程序在这里被打断, 那么会清除打断标记(因为线程处于 sleep 阻塞状态), 需要重新设置打断标记为 true
                    TimeUnit.MILLISECONDS.sleep(1000);
                    // 情况二: 如果线程在这里被打断, 那么不会清除打断标记(因为线程处于正常状态)
                    log.info("监控运行, 保存监控结果");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "monitor-thread");
        monitorThread.start();

        TimeUnit.SECONDS.sleep(3);
        stop = true;
    }

}
