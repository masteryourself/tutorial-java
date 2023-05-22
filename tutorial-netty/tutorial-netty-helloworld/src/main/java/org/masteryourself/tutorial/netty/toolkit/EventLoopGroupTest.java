package org.masteryourself.tutorial.netty.toolkit;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : EventLoopGroupTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/11 1:51 PM
 */
@Slf4j
public class EventLoopGroupTest {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new DefaultEventLoopGroup(2);
        for (int i = 0; i < 5; i++) {
            log.info("获取对象 {}", eventLoopGroup.next());
        }
    }

}
