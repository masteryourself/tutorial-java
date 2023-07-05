package org.masteryourself.tutorial.concurrent.thread.state;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : ThreadState
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/13 14:39
 */
@Slf4j
public class ThreadState {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (ThreadState.class) {
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (ThreadState.class) {
            }
        }, "t6");
        t6.start();

        // t1 state NEW
        log.info("t1 state {}", t1.getState());
        // t2 state RUNNABLE
        log.info("t2 state {}", t2.getState());
        // t3 state TERMINATED
        log.info("t3 state {}", t3.getState());
        // t4 state TIMED_WAITING
        log.info("t4 state {}", t4.getState());
        // t5 state WAITING
        log.info("t5 state {}", t5.getState());
        // t6 state BLOCKED
        log.info("t6 state {}", t6.getState());
    }

}
