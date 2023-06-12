package org.masteryourself.tutorial.concurrent.sync.waitnotify;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : GuardedObject
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/8/14 12:50
 */
@Slf4j
public class GuardedObject {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();

        new Thread(() -> {
            Object response = guardedObject.get(3000);
            log.info("等外卖(最多 3s, 不然就超时了): {}", response);
        }, "顾客").start();

        new Thread(() -> {
            log.info("接收到订单, 开始送外卖");
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.set("黄焖鸡米饭");
            log.info("外卖已存储至快递柜");
        }, "外卖小哥").start();
    }

    private Object response;
    private final Object lock = new Object();

    public Object get(long timeout) {
        // 记录最开始时间
        long beginTime = System.currentTimeMillis();
        // 记录已经经历的时间
        long passTime = 0L;
        synchronized (lock) {
            while (response == null) {
                // 本轮最多可以等待的时间
                long waitTime = timeout - passTime;
                // 超时退出等待
                if (waitTime <= 0) {
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 计算已经消耗的时间
                passTime = System.currentTimeMillis() - beginTime;
            }
        }
        return response;
    }

    public void set(Object response) {
        synchronized (lock) {
            this.response = response;
            // 条件满足, 通知线程
            lock.notifyAll();
        }
    }

}
