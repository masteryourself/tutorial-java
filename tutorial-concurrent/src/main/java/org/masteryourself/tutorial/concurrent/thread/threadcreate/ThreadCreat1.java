package org.masteryourself.tutorial.concurrent.thread.threadcreate;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ThreadCreat1
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/24 11:05 PM
 */
@Slf4j
public class ThreadCreat1 {

    public static void main(String[] args) {
        // 创建线程对象
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                // 要执行的任务
                log.info("t1 run");
            }
        };
        // 启动线程
        t1.start();
    }

}
