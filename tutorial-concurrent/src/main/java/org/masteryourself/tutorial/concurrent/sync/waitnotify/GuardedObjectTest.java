package org.masteryourself.tutorial.concurrent.sync.waitnotify;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : GuardedObjectTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/25 21:54
 */
@Slf4j
public class GuardedObjectTest {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();

        new Thread(() -> {
            // Object response = guardedObject.get();
            Object response = guardedObject.get(3000);
            log.info("等外卖(最多 3s, 不然就超时了): {}", response);
        }, "顾客").start();

        new Thread(() -> {
            log.info("接收到订单, 开始送外卖");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = "黄焖鸡米饭";
            guardedObject.set(response);
            log.info("[{}] 外卖已存储至快递柜", response);
        }, "外卖小哥").start();
    }

}
