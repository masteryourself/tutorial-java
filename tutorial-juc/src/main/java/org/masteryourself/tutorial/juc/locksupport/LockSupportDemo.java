package org.masteryourself.tutorial.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>description : LockSupportDemo
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/23 5:00 PM
 */
public class LockSupportDemo {

    public static void main(String[] args) throws Exception {
        LockSupportDemo demo = new LockSupportDemo();
        demo.demo1();
        demo.demo2();
    }

    public void demo1() throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "_进入");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "_执行结束");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            LockSupport.unpark(t1);
        }, "t2").start();
    }

    public void demo2() throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "_进入");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "_执行结束");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
        }, "t2").start();
    }

}
