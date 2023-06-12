package org.masteryourself.tutorial.designpattern.structual.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : FlyweightTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 3:05 PM
 */
@Slf4j
public class FlyweightTest {

    public static void main(String[] args) throws Exception {
        // 这里必须 sleep 否则会发生线程安全
        Spa spa = new Spa();
        relax(spa, "张三");
        TimeUnit.SECONDS.sleep(1);
        relax(spa, "李四");
        TimeUnit.SECONDS.sleep(1);
        relax(spa, "王五");
    }

    private static void relax(Spa spa, String customer) {
        new Thread(() -> {
            System.out.println(customer + "开始做足疗");
            Waitress waitress = spa.getWaitress();
            waitress.start(customer);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            waitress.stop(customer);
        }).start();
    }

}
