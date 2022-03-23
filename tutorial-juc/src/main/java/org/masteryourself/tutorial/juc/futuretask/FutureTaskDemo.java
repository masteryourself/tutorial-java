package org.masteryourself.tutorial.juc.futuretask;

import java.util.concurrent.*;

/**
 * <p>description : FutureTaskDemo
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/22 11:17 AM
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        FutureTaskDemo demo = new FutureTaskDemo();
        demo.demo1();
        demo.demo2();
    }

    public void demo1() throws Exception {
        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "任务执行完毕";
        });
        new Thread(task, "task").start();
        // 3 秒钟后才出来结果，还没有计算你提前来拿(只要一调用 get() 方法，对于结果就是不见不散，会导致阻塞)
        System.out.println(Thread.currentThread().getName() + "\t" + task.get());

        // 3 秒钟后才出来结果，我只想等待 1 秒钟，过时不候
        // System.out.println(Thread.currentThread().getName() + "\t" + task.get(1L, TimeUnit.SECONDS));
    }

    public void demo2() throws Exception {
        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "任务执行完毕";
        });
        new Thread(task, "task").start();

        while (!task.isDone()) {
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + task.get(1L, TimeUnit.SECONDS));
    }

}
