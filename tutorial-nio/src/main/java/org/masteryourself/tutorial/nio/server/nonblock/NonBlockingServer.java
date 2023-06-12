package org.masteryourself.tutorial.nio.server.nonblock;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.nio.bytebuffer.ByteBufferUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : NonBlockingServer
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 7:26 PM
 */
@Slf4j
public class NonBlockingServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9527));
        ssc.configureBlocking(false);
        List<SocketChannel> channelList = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        while (true) {
            // 这里不会再阻塞
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                log.info("connected {}", sc);
                sc.configureBlocking(false);
                channelList.add(sc);
            }
            for (SocketChannel channel : channelList) {
                // 这里不会再阻塞
                int bytes = channel.read(buffer);
                if (bytes <= 0) {
                    continue;
                }
                // 切换到读模式
                buffer.flip();
                ByteBufferUtil.debugRead(buffer);
                // 清除
                buffer.clear();
            }
        }
    }

}
