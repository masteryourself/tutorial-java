package org.masteryourself.tutorial.nio.server.selector.multi;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : BossEventLoop
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 5:12 PM
 */
@Slf4j
public class BossEventLoop implements Runnable {

    private Selector boss;
    private WorkEventLoop[] works;
    private final AtomicInteger requestIndex = new AtomicInteger(0);

    public void register() {
        try {
            boss = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9527));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(boss, SelectionKey.OP_ACCEPT, null);
            this.initWorkEventLoop();
            new Thread(this, "boss").start();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void initWorkEventLoop() {
        works = new WorkEventLoop[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < works.length; i++) {
            works[i] = new WorkEventLoop(i);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                boss.select();
                Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    // boss 线程只处理连接事件
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        log.info("连接建立事件 {}", socketChannel);
                        // 轮训交给 work 处理
                        socketChannel.configureBlocking(false);
                        works[requestIndex.incrementAndGet() % works.length].register(socketChannel);
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
