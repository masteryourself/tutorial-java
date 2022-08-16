package org.masteryourself.tutorial._volatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : Volatile2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 23:16
 */
@Slf4j
public class Volatile3 {

    static final Object lock = new Object();
    static boolean run = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (!run) {
                        log.info("线程停下来了");
                        break;
                    }
                }
            }
        }, "t");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            run = false; // 线程t不会如预想的停下来
        }
    }

}
