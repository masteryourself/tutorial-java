package org.masteryourself.tutorial.thread.startrun;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ThreadStart
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 12:33 AM
 */
@Slf4j
public class ThreadStart {

    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.info("t1 start");
            }
        };
        // 这里是 main 线程打印日志
        thread.run();
        // 这里是 t1 新线程打印日志
        thread.start();
    }

}
