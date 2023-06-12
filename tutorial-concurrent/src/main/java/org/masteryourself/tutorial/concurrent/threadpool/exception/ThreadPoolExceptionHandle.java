package org.masteryourself.tutorial.concurrent.threadpool.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * <p>description : ThreadPoolExceptionHandle
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/10/22 18:57
 */
@Slf4j
public class ThreadPoolExceptionHandle {

    public static void main(String[] args) throws Exception {
        exceptionHandle1();
        exceptionHandle2();
        exceptionHandle3();
    }

    private static void exceptionHandle1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> {
            try {
                int i = 10 / 0;
            } catch (Exception e) {
                log.error("线程池运行异常", e);
            }
            log.info("任务运行结束");
        });
    }

    private static void exceptionHandle2() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<?> future = threadPool.submit(() -> {
            int i = 10 / 0;
            log.info("任务运行结束");
        });
        log.info("res {}", future.get());
    }

    private static void exceptionHandle3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler((t, e) -> log.info("线程池运行异常", e));
                return thread;
            }
        });
        threadPool.execute(() -> {
            int i = 10 / 0;
            log.info("任务运行结束");
        });
    }

}
