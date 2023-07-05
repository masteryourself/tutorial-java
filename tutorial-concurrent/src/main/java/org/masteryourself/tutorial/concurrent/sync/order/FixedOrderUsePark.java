package org.masteryourself.tutorial.concurrent.sync.order;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>description : FixedOrderUsePark
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 11:58
 */
@Slf4j
public class FixedOrderUsePark {

    private static volatile boolean printFlag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            if (!printFlag) {
                LockSupport.park();
            }
            log.info("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("2");
            printFlag = true;
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }

}
