package org.masteryourself.tutorial.concurrent.volatilee;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : ForeverLoop
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/27 16:19
 */
@Slf4j
public class ForeverLoop {

    static boolean run = true;

    // 这个问题本质是由于 JIT 编译器优化导致的问题, 可以添加 -Xint 参数来禁止 JIT 编译优化 
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
            }
            log.info("线程停下来了");
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        // 线程t 不会如预想的停下来
        run = false;
    }

}
