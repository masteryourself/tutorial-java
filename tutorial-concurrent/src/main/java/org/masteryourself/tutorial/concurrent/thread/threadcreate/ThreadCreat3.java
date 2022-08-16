package org.masteryourself.tutorial.concurrent.thread.threadcreate;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : ThreadCreat2
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/24 11:06 PM
 */
@Slf4j
public class ThreadCreat3 {

    public static void main(String[] args) throws Exception {
        // 创建任务对象
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("t3 run");
                TimeUnit.SECONDS.sleep(1);
                return 100;
            }
        });
        // 启动线程
        new Thread(futureTask, "t3").start();
        // 主线程阻塞, 同步等待任务运行结果
        Integer result = futureTask.get();
        log.info("结果是 {}", result);
    }

}
