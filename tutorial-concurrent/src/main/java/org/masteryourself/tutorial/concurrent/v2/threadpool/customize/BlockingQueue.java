package org.masteryourself.tutorial.concurrent.v2.threadpool.customize;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>description : BlockingQueue
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/15 14:20
 */
@Slf4j
public class BlockingQueue<T> {

    // 任务队列
    private final Deque<T> queue = new ArrayDeque<>();

    // 锁
    private final ReentrantLock lock = new ReentrantLock();

    // 队列已满条件变量
    private final Condition fullWaitSet = lock.newCondition();

    // 队列为空条件变量
    private final Condition emptyWaitSet = lock.newCondition();

    // 容量
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 带超时的阻塞获取任务
     *
     * @param timeout 时间
     * @param unit    时间单位
     * @return T
     */
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            // 1. 如果队列大小已经满了, 这里阻塞等待
            while (queue.isEmpty()) {
                // 如果时间小于等于 0 表示超时
                if (nanos <= 0) {
                    return null;
                }
                try {
                    log.info("等待从任务队列中获取任务");
                    // 这个 api 返回值表示传入时间 - 等待耗费的时间
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 2. 走到这里说明可以从队列中获取任务
            T task = queue.removeFirst();
            log.info("成功从任务队列中获取任务 <<< {}", task);
            // 3. 唤醒生产者线程, 可以继续添加任务了
            fullWaitSet.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取任务
     *
     * @return T
     */
    public T take() {
        lock.lock();
        try {
            // 1. 如果队列大小已经满了, 这里阻塞等待
            while (queue.isEmpty()) {
                try {
                    log.info("等待从任务队列中获取任务");
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 2. 走到这里说明可以从队列中获取任务
            T task = queue.removeFirst();
            log.info("成功从任务队列中获取任务 <<< {}", task);
            // 3. 唤醒生产者线程, 可以继续添加任务了
            fullWaitSet.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时的阻塞添加任务
     *
     * @param task    任务
     * @param timeout 时间
     * @param unit    时间单位
     * @return 任务是否添加成功
     */
    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            // 1. 如果队列大小已经满了, 这里阻塞等待
            while (queue.size() == this.capacity) {
                // 如果时间小于等于 0 表示超时
                if (nanos <= 0) {
                    return false;
                }
                try {
                    log.info("等待加入到任务队列中 >>> {}", task);
                    // 这个 api 返回值表示传入时间 - 等待耗费的时间
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 2. 走到这里说明可以加入到任务队列中
            queue.addLast(task);
            log.info("成功加入到任务队列中 >>> {}", task);
            // 3. 唤醒消费者线程, 可以继续消费任务了
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞添加任务
     */
    public void put(T task) {
        lock.lock();
        try {
            // 1. 如果队列大小已经满了, 这里阻塞等待
            while (queue.size() == this.capacity) {
                try {
                    log.info("等待加入到任务队列中 >>> {}", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 2. 走到这里说明可以加入到任务队列中
            queue.addLast(task);
            log.info("成功加入到任务队列中 >>> {}", task);
            // 3. 唤醒消费者线程, 可以继续消费任务了
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            // 如果队列已满, 执行拒绝策略中的方法
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            }
            // 如果队列未满, 直接添加
            else {
                queue.addLast(task);
                log.info("成功加入到任务队列中 >>> {}", task);
                // 唤醒消费者线程, 可以继续消费任务了
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
