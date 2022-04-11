package org.masteryourself.tutorial.nio.server.selector.read;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : SelectorReadClient
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 1:52 PM
 */
public class SelectorReadClient {

    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(9527));
        System.out.println("waiting");
        sc.write(StandardCharsets.UTF_8.encode("start\nHello, I'm masteryourself\nend\n"));
    }

}
