package org.masteryourself.tutorial.concurrent.thread.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : 二阶段终止模式, 使用 interrupt 完成
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/13 13:24
 */
@Slf4j
public class TPTInterrupt {

    public static void main(String[] args) throws Exception {
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Thread current = Thread.currentThread();
                    if (current.isInterrupted()) {
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
                        // 线程当前打断状态是-1 false
                        log.info("线程当前打断状态是-1 {}", current.isInterrupted());
                        current.interrupt();
                        // 线程当前打断状态是-2 true
                        log.info("线程当前打断状态是-2 {}", current.isInterrupted());
                    }
                }
            }
        }, "t3");
        t3.start();
        TimeUnit.SECONDS.sleep(3);
        t3.interrupt();
    }

}
