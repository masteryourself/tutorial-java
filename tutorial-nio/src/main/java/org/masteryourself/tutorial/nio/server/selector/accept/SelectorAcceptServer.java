package org.masteryourself.tutorial.nio.server.selector.accept;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>description : SelectorAcceptServer
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/18 17:25
 */
@Slf4j
public class SelectorAcceptServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(9527));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            // 绑定 channel 的 ACCEPT 事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // select 方法, 没有事件发生，线程阻塞，有事件，线程才会恢复运行
                selector.select();
                // 获取到所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历所有事件，挨个处理
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 判断事件类型
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        // 必须处理
                        SocketChannel socketChannel = channel.accept();
                        log.info("连接建立事件 {}", socketChannel);
                    }
                    // 处理完毕，必须将事件移除
                    iterator.remove();
                }
            }
        }
    }

}
