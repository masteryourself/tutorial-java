package org.masteryourself.tutorial.juc.completablefuture;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * <p>description : CompletableFutureTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/22 1:59 PM
 */
public class CompletableFutureTest {

    @Test
    public void test1() throws Exception {
        Void res = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程名称:" + Thread.currentThread().getName());
        }).get();
        System.out.println(res);
    }

    @Test
    public void test2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        Integer res = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程名称:" + Thread.currentThread().getName());
            return 10;
        }, executor).join();
        // 使用 join() 不抛出异常
        System.out.println(res);
    }

    @Test
    public void test3() {
        Integer res = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }).thenApplyAsync((r1) -> r1 * r1).whenComplete((r, e) -> {
            if (e == null) {
                System.out.println("res =" + r);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        }).join();
        System.out.println(res);
    }

    @Test
    public void test4() {
        long start = System.currentTimeMillis();
        Integer res = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        }), (r1, r2) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return r1 + r2;
        }).join();
        System.out.printf("结果是 %s, 耗时 %d s%n", res, System.currentTimeMillis() - start);
    }

}
