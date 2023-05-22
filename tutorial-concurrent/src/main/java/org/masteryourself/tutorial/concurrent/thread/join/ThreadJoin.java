package org.masteryourself.tutorial.concurrent.thread.join;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ThreadJoin
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 10:55 AM
 */
@Slf4j
public class ThreadJoin {

    static int result = 1;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> result = 10, "t1");
        t1.start();
        // 确保线程运行结束, 将结果改成 10
        t1.join();
        log.info("result {}", result);
    }

}
