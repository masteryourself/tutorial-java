package org.masteryourself.tutorial.nio.server.nonblock;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : NonBlockingClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 7:29 PM
 */
public class NonBlockingClient {

    public static void main(String[] args) throws Exception {
        try (SocketChannel sc = SocketChannel.open();) {
            sc.connect(new InetSocketAddress(9527));
            sc.write(StandardCharsets.UTF_8.encode("非阻塞模式"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
