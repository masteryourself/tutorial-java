package org.masteryourself.tutorial.nio.server.selector.read;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : SelectorReadClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 1:52 PM
 */
@Slf4j
public class SelectorReadClient {

    public static void main(String[] args) throws Exception {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.connect(new InetSocketAddress(9527));
            log.info("建立连接 {}", sc);
            sc.write(StandardCharsets.UTF_8.encode("start\nHello, I'm masteryourself\nend\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
