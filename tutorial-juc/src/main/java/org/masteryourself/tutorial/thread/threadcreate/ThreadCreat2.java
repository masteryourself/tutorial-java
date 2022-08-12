package org.masteryourself.tutorial.thread.threadcreate;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ThreadCreat2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/24 11:06 PM
 */
@Slf4j
public class ThreadCreat2 {

    public static void main(String[] args) {
        // 要执行的任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("t2 run");
            }
        };
        // 创建线程
        Thread t2 = new Thread(runnable, "t2");
        // 启动线程
        t2.start();
    }

}
