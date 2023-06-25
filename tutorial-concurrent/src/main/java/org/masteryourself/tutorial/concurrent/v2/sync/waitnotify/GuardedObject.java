package org.masteryourself.tutorial.concurrent.v2.sync.waitnotify;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : GuardedObject
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 12:50
 */
@Slf4j
public class GuardedObject {

    private Object response;
    private final Object lock = new Object();

    public Object get() {
        synchronized (lock) {
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public Object get(long timeout) {
        // 记录最开始时间
        long beginTime = System.currentTimeMillis();
        // 记录已经经历的时间
        long passTime = 0L;
        synchronized (lock) {
            while (response == null) {
                // 本轮最多可以等待的时间
                long waitTime = timeout - passTime;
                // 超时退出等待
                if (waitTime <= 0) {
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 计算已经消耗的时间
                passTime = System.currentTimeMillis() - beginTime;
            }
        }
        return response;
    }

    public void set(Object response) {
        synchronized (lock) {
            this.response = response;
            // 条件满足, 通知线程
            lock.notifyAll();
        }
    }

}
