package org.masteryourself.tutorial.juc.threadcreate;

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
        new Thread("t1") {
            @Override
            public void run() {
                log.info("t1 run");
            }
        }.start();
    }

}
