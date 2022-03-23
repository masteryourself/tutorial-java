package org.masteryourself.tutorial.juc.interrupt;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : InterruptTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/23 12:39 PM
 */
public class InterruptTest {

    @Test
    public void test3() throws Exception {
        // 当前线程状态_main_false
        System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.interrupted());
        // 当前线程状态_main_false
        System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.interrupted());
        Thread.currentThread().interrupt();
        // 发出中断标识_main_true
        System.out.println("发出中断标识_" + Thread.currentThread().getName() + "_" + Thread.currentThread().isInterrupted());
        // 当前线程状态_main_true Thread.interrupted() 会先返回当前线程的中断状态, 然后清除中断状态(即还原成 false)
        System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.interrupted());
        // 当前线程状态_main_false
        System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.interrupted());
    }

    public static void main(String[] args) throws Exception {
        new InterruptTest().test2();
    }

    @Test
    public void test2() throws Exception {
        // 注意这段代码放在 junit 环境中可能有问题, 符合不了预期效果, 但问题是存在的
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序中断退出");
                    break;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.currentThread().isInterrupted());
                    // 因为程序在 sleep() 之后抛出了 InterruptedException, 同时清理了中断标识, 所以这里需要再次设置中断标识
                    Thread.currentThread().interrupt();
                    System.out.println("当前线程状态_" + Thread.currentThread().getName() + "_" + Thread.currentThread().isInterrupted());
                }
                System.out.println("程序继续运行");
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        // 这里只是发出中断标识, 但是程序会继续执行结束
        new Thread(t1::interrupt, "t2").start();
    }

    @Test
    public void test1() throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println(i + "_" + Thread.currentThread().getName() + "_" + Thread.currentThread().isInterrupted());
            }
        }, "t1");
        t1.start();
        TimeUnit.MICROSECONDS.sleep(5);
        // 这里只是发出中断标识, 但是程序会继续执行结束
        t1.interrupt();
        System.out.println("发出中断标识_" + t1.getName() + "_" + t1.isInterrupted());
    }

}
