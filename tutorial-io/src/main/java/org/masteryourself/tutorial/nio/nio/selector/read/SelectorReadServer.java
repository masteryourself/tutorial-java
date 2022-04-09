package org.masteryourself.tutorial.nio.nio.selector.read;

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
import java.util.concurrent.TimeUnit;

/**
 * <p>description : SelectorReadServer
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 1:40 PM
 */
@Slf4j
public class SelectorReadServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(9527));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            // 绑定 channel 的 ACCEPT 事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // count 表示有多少个 channel 发生了事件
                int count = selector.select();
                if (count <= 0) {
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                // 获取到所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历所有事件，挨个处理
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 判断事件类型
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        log.info("连接建立事件 {}", socketChannel);
                        // 绑定 channel 的 READ 事件
                        socketChannel.configureBlocking(false);
                        socketChannel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        // 使用 attach 传递 buffer
                        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        // 从 selectionKey 获取到 attach(buffer)
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        // 把客户端数据读到缓冲区中
                        int read = channel.read(buffer);
                        if (read == -1) {
                            log.warn("未读取到客户端任何数据, 关闭该连接{}", channel);
                            // 客户端正常、异常关闭时都会触发 read 事件，而事件要么处理，要么取消，否则下次该事件仍会触发
                            selectionKey.cancel();
                            channel.close();
                        } else {
                            split(buffer);
                            // 说明未找到, 这个 buffer 不够需要扩容
                            if (buffer.limit() == buffer.position()) {
                                ByteBuffer target = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                target.put(buffer);
                                // 替换原来的 buffer
                                buffer = null;
                                selectionKey.attach(target);
                            }
                        }
                    }
                    // 处理完成后, 必须要移除事件
                    iterator.remove();
                }
            }
        }
    }

    private static void split(ByteBuffer source) {
        // 切换到读模式
        source.flip();
        // 判断是否有结尾标识符
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int len = i - source.position() + 1;
                ByteBuffer target = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }
                // 切换到读模式, 打印数据
                target.flip();
                log.info("读取到客户端数据 {}", StandardCharsets.UTF_8.decode(target));
            }
        }
        // 压缩, 将未拆解的信息继续存入到 source 中
        source.compact();
    }

}
