package org.masteryourself.tutorial.concurrent.v2.threadpool.customize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : MyThreadPoolTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/15 14:41
 */
@Slf4j
public class MyThreadPoolTest {

    public static void main(String[] args) {
        RejectPolicy<Runnable> policy = (queue, task) -> {
            // 1. 死等
            // queue.put(task);
            // 2. 带超时等待
            // queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 3. 让调用者放弃任务执行
            // log.info("放弃执行任务 {}", task);
            // 4. 让调用者抛出异常
            // throw new RuntimeException("任务执行失败" + task);
            // 5. 让调用者自己执行任务
            task.run();
        };
        MyThreadPool myThreadPool = new MyThreadPool(1, 2, 1, TimeUnit.SECONDS, policy);
        for (int i = 0; i < 5; i++) {
            int j = i;
            myThreadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("任务执行完成, 结果是 {}", j);
            });
        }
    }

}
