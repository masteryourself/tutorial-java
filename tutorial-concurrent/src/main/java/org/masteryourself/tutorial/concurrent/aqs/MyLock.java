package org.masteryourself.tutorial.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>description : 自定义非公平不可重入锁
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/30 13:01
 */
public class MyLock implements Lock {

    private final MySync sync = new MySync();

    /**
     * 尝试获取锁，不成功进入等待队列
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 尝试获取锁，不成功进入等待队列，可打断
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试一次，不成功返回，不进入队列
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 尝试在有效时间内获取锁，不成功进入等待队列
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * 生成条件变量
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    static class MySync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int acquires) {
            if (acquires == 1) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int acquires) {
            if (acquires == 1) {
                if (getState() == 0) {
                    throw new IllegalStateException();
                }
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }
}
