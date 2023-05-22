package org.masteryourself.tutorial.concurrent._volatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : Volatile1
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 23:09
 */
@Slf4j
public class Volatile1 {

    static boolean run = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                if (!run) {
                    log.info("线程停下来了");
                    break;
                }
            }
        }, "t");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        run = false; // 线程t不会如预想的停下来
    }

}
