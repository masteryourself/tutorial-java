package org.masteryourself.tutorial.concurrent.thread.join;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : ThreadTimeJoin
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/24 19:36
 */
@Slf4j
public class ThreadTimeJoin {

    static int result = 1;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = 10;
        }, "t1");
        long start = System.currentTimeMillis();
        t1.start();
        // 最多等待 1.5s
        t1.join(1500);
        long end = System.currentTimeMillis();
        log.info("result {}, cost {} ms", result, end - start);
    }

}
