package org.masteryourself.tutorial.juc.join;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : ThreadJoin
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/25 10:55 AM
 */
@Slf4j
public class ThreadJoin {

    static int result = 1;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> result = 10, "t1");
        thread.start();
        // 确保线程运行结束, 将结果改成 10
        thread.join();
        log.info("result {}", result);
    }

}
