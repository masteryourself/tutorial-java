package org.masteryourself.tutorial.algorithm.queue.blockingqueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : ArrayBlockingQueueTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 11:41
 */
class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        queue.offer("元素1");
        queue.offer("元素2");

        new Thread(() -> {
            try {
                queue.offer("元素3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }

}