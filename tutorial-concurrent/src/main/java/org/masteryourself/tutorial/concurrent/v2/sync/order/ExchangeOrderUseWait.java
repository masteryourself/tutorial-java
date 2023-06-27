package org.masteryourself.tutorial.concurrent.v2.sync.order;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : ExchangeOrderWait
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 14:01
 */
@Slf4j
public class ExchangeOrderUseWait extends Thread {

    private final int threadTag;
    private final int nextThreadTag;
    private final String content;
    private static final AtomicInteger EXECUTE_TAG = new AtomicInteger(1);

    public ExchangeOrderUseWait(int threadTag, int nextThreadTag, String content) {
        this.threadTag = threadTag;
        this.nextThreadTag = nextThreadTag;
        this.content = content;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (ExchangeOrderUseWait.class) {
                while (EXECUTE_TAG.get() != threadTag) {
                    try {
                        ExchangeOrderUseWait.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info(content);
                EXECUTE_TAG.set(nextThreadTag);
                ExchangeOrderUseWait.class.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        new ExchangeOrderUseWait(1, 2, "a").start();
        new ExchangeOrderUseWait(2, 3, "b").start();
        new ExchangeOrderUseWait(3, 1, "c").start();
    }

}
