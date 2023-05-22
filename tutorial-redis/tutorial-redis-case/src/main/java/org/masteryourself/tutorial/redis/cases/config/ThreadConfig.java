package org.masteryourself.tutorial.redis.cases.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : ThreadConfig
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/22 9:07 PM
 */
@Slf4j
@Configuration
public class ThreadConfig {

    @Bean
    public ThreadPoolExecutor cacheBuildThreadPool() {
        return new ThreadPoolExecutor(
                5,
                10,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> new Thread(r, "cache-build-"),
                (r, executor) -> {
                    log.warn("cache-build 线程池任务已满");
                    r.run();
                });
    }

}
