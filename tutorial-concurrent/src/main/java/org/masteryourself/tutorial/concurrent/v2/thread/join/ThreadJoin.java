package org.masteryourself.tutorial.concurrent.v2.thread.join;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : Demo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/24 18:14
 */
@Slf4j
public class ThreadJoin {

    static int result = 1;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = 10;
        }, "t1");
        t1.start();
        // 确保线程运行结束, 将结果改成 10
        t1.join();
        log.info("result {}", result);
    }

}
