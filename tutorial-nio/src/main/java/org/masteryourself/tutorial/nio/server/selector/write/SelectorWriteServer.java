package org.masteryourself.tutorial.nio.server.selector.write;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>description : SelectorWriteServer
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 3:20 PM
 */
@Slf4j
public class SelectorWriteServer {

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
                    // 处理key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                    iterator.remove();
                    // 判断事件类型
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        log.info("连接建立事件 {}", socketChannel);
                        // 绑定 channel 的 READ 事件
                        socketChannel.configureBlocking(false);
                        SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_READ);
                        StringBuilder sb = new StringBuilder();
                        // 一旦建立连接, 服务端就开始向客户端发送数据
                        for (int i = 0; i < 3000000; i++) {
                            sb.append("a");
                        }
                        ByteBuffer buffer = StandardCharsets.UTF_8.encode(sb.toString());
                        int write = socketChannel.write(buffer);
                        log.info("本次共写入 {} 个字节", write);
                        // 如果有剩余未读字节，才需要关注写事件
                        if (buffer.hasRemaining()) {
                            log.info("需要关注写事件");
                            // 在原有事件的基础上, 多添加一个关注写事件
                            sk.interestOps(sk.interestOps() + SelectionKey.OP_WRITE);
                            // 使用 attach 传递 buffer
                            sk.attach(buffer);
                        }
                    } else if (selectionKey.isWritable()) {
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        int write = channel.write(buffer);
                        log.info("本次共写入 {} 个字节", write);
                        // 如果已经写完了
                        if (!buffer.hasRemaining()) {
                            selectionKey.interestOps(selectionKey.interestOps() - SelectionKey.OP_WRITE);
                            selectionKey.attach(null);
                        }
                    }
                }
            }
        }
    }

}
