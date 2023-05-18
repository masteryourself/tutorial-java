package org.masteryourself.tutorial.algorithm.queue.blockingqueue;

/**
 * <p>description : BlockingQueue
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/18 11:38
 */
public interface BlockingQueue<E> {

    void offer(E e) throws InterruptedException;

    boolean offer(E e, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;

}
