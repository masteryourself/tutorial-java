package org.masteryourself.tutorial.concurrent.threadpool.customize;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : MyThreadPool
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/15 13:51
 */
@Slf4j
public class MyThreadPool {

    // 核心线程数量
    private final int coreSize;

    // 任务队列
    private final BlockingQueue<Runnable> taskQueue;

    // 获取任务超时时间
    private final long timeout;

    // 超时时间单位
    private final TimeUnit timeUnit;

    // 拒绝策略
    private final RejectPolicy<Runnable> rejectPolicy;

    // 工作线程集合
    private final HashSet<Worker> workers = new HashSet<>();

    public MyThreadPool(int coreSize, int capacity, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(capacity);
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            // 当任务数没有超过最大线程时, 可以新创建一个线程执行任务
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
                log.info("线程池新增线程 {}", worker);
            }
            // 如果超过了最大线程数, 尝试保存到任务队列中(队列已满使用拒绝策略)
            else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // task 有两处来源: ①是通过构造方法传入, ②是通过队列获取
            while (task != null || ((task = taskQueue.poll(timeout, timeUnit)) != null)) {
                try {
                    log.info("正在执行任务 {}", task);
                    task.run();
                } catch (Exception e) {
                    log.error("线程池处理任务失败", e);
                } finally {
                    task = null;
                }
            }
            // 如果任务全部运行完成, 需要释放线程池
            synchronized (workers) {
                log.info("移除线程池中的线程 {}", this);
                workers.remove(this);
            }
        }
    }

}
