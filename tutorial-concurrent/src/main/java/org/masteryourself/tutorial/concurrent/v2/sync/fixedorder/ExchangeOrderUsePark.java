package org.masteryourself.tutorial.concurrent.v2.sync.fixedorder;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>description : ExchangeOrderUsePark
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 14:46
 */
@Slf4j
public class ExchangeOrderUsePark {

    private Thread[] threads;

    public void initThread(Thread... threads) {
        this.threads = threads;
    }

    public void print(String content) {
        for (int i = 0; i < 5; i++) {
            LockSupport.park();
            log.info(content);
            LockSupport.unpark(nextThread());
        }
    }

    private Thread nextThread() {
        Thread current = Thread.currentThread();
        int index = 0;
        for (int i = 0; i < threads.length; i++) {
            if (threads[i] == current) {
                index = i;
                break;
            }
        }
        if (index < threads.length - 1) {
            return threads[index + 1];
        } else {
            return threads[0];
        }
    }

    public static void main(String[] args) {
        ExchangeOrderUsePark park = new ExchangeOrderUsePark();
        Thread t1 = new Thread(() -> park.print("a"), "t1");
        Thread t2 = new Thread(() -> park.print("b"), "t2");
        Thread t3 = new Thread(() -> park.print("c"), "t3");
        park.initThread(t1, t2, t3);

        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }

}
