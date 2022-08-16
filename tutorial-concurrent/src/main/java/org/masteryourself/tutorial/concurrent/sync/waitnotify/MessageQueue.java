package org.masteryourself.tutorial.concurrent.sync.waitnotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : MessageQueue
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 17:31
 */
@Slf4j
public class MessageQueue {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                Message message = new Message(finalI, "消息" + finalI);
                queue.put(message);
            }, "p" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
                log.info("消费消息 {}", message);
            }
        }, "c").start();
    }

    private final LinkedList<Message> queue;

    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public Message take() {
        synchronized (queue) {
            while (this.queue.isEmpty()) {
                try {
                    log.info("队列空了, 等待");
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = this.queue.removeFirst();
            this.queue.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (queue) {
            while (this.queue.size() >= this.capacity) {
                try {
                    log.info("队列满了, 等待");
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("生产消息 {}", message);
            this.queue.add(message);
            this.queue.notifyAll();
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class Message {
        private int id;
        private Object message;
    }

}
