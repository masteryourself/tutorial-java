package org.masteryourself.tutorial.nio.nio.selector.multi;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : SelectorMultiServer
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 4:21 PM
 */
@Slf4j
public class SelectorMultiServer {

    public static void main(String[] args) throws Exception {
        BossEventLoop bossEventLoop = new BossEventLoop();
        bossEventLoop.register();
    }

}
