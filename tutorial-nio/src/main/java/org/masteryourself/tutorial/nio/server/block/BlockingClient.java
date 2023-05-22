package org.masteryourself.tutorial.nio.server.block;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : BlockingClient
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 5:55 PM
 */
public class BlockingClient {

    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(9527));
        System.out.println("waiting");
        sc.write(StandardCharsets.UTF_8.encode("hello"));
    }

}
