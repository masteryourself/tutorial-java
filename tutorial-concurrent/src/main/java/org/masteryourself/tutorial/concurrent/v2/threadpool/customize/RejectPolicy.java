package org.masteryourself.tutorial.concurrent.v2.threadpool.customize;

/**
 * <p>description : RejectPolicy
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/15 14:20
 */
@FunctionalInterface
public interface RejectPolicy<T> {

    // 拒绝策略
    void reject(BlockingQueue<T> queue, T task);

}
