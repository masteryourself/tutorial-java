package org.masteryourself.tutorial.nio.server.selector.write;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>description : SelectorWriteClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 3:20 PM
 */
@Slf4j
public class SelectorWriteClient {

    public static void main(String[] args) throws Exception {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.configureBlocking(false);
            Selector selector = Selector.open();
            sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
            sc.connect(new InetSocketAddress(9527));
            int total = 0;
            while (true) {
                // select 方法, 没有事件发生，线程阻塞，有事件，线程才会恢复运行
                selector.select();
                // 获取到所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历所有事件，挨个处理
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 处理key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                    iterator.remove();
                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        // 一定要调用, 完成连接
                        channel.finishConnect();
                        log.info("客户端连接成功 {}", channel);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                        total += channel.read(buffer);
                        buffer.clear();
                        log.info("一共读取了 {} 个字节", total);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
