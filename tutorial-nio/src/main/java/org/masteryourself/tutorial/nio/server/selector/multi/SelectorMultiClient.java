package org.masteryourself.tutorial.nio.server.selector.multi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : SelectorMultiClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 5:37 PM
 */
public class SelectorMultiClient {

    public static void main(String[] args) throws Exception {
        try (SocketChannel sc = SocketChannel.open();) {
            sc.connect(new InetSocketAddress(9527));
            sc.write(StandardCharsets.UTF_8.encode("你好呀"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
