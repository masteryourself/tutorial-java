package org.masteryourself.tutorial.nio.server.block;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.nio.bytebuffer.ByteBufferUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : BlockingServer
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 5:47 PM
 */
@Slf4j
public class BlockingServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9527));
        List<SocketChannel> channelList = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        while (true) {
            log.info("connecting");
            // 这一步会阻塞
            SocketChannel sc = ssc.accept();
            log.info("connected {}", sc);
            channelList.add(sc);
            for (SocketChannel channel : channelList) {
                // 把数据读取到 buffer 缓冲区, 这一步也会阻塞
                channel.read(buffer);
                // 切换到读模式
                buffer.flip();
                ByteBufferUtil.debugRead(buffer);
                // 清除
                buffer.clear();
            }
        }
    }

}
