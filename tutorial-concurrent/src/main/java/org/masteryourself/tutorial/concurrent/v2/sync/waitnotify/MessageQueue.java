package org.masteryourself.tutorial.concurrent.v2.sync.waitnotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * <p>description : MessageQueue
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 17:31
 */
@Slf4j
public class MessageQueue {

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
