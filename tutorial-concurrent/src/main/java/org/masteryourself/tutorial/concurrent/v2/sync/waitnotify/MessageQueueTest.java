package org.masteryourself.tutorial.concurrent.v2.sync.waitnotify;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : MessageQueueTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/25 22:02
 */
@Slf4j
public class MessageQueueTest {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                MessageQueue.Message message = new MessageQueue.Message(finalI, "消息" + finalI);
                queue.put(message);
            }, "P" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MessageQueue.Message message = queue.take();
                log.info("消费消息 {}", message);
            }
        }, "C").start();
    }

}
