package org.masteryourself.tutorial.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ShareQuestionSync
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 11:03 PM
 */
@Slf4j
public class ShareQuestionSync {

    static int counter = 0;

    static final Object room = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("counter 结果是 {}", counter);
    }

}
