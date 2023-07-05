package org.masteryourself.tutorial.concurrent.volatilee;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : Autoincrement
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 16:31
 */
@Slf4j
public class Autoincrement {

    private static volatile int count = 1000;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count--;
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // 这里的结果不一定是 1000
        log.info("count 最终结果是 {}", count);
    }

}
