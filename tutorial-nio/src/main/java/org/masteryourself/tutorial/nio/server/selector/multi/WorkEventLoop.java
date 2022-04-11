package org.masteryourself.tutorial.nio.server.selector.multi;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>description : WorkEventLoop
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/9 5:12 PM
 */
@Slf4j
public class WorkEventLoop implements Runnable {

    private Selector work;
    private final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();

    public WorkEventLoop(int index) {
        try {
            work = Selector.open();
            new Thread(this, "work-" + index).start();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void register(SocketChannel socketChannel) {
        tasks.add(() -> {
            try {
                SelectionKey selectionKey = socketChannel.register(work, 0, null);
                selectionKey.interestOps(SelectionKey.OP_READ);
                work.selectNow();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
        // 调用 wakeup() 方法让 select() 不再阻塞，所以代码才能走到 tasks.poll() 否则将会一直阻塞住
        work.wakeup();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 它会导致阻塞, 无法 channel 无法注册到 Selector 上
                work.select();
                Runnable task = tasks.poll();
                if (task != null) {
                    task.run();
                }
                Iterator<SelectionKey> iterator = work.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        channel.read(buffer);
                        // 切换到读模式
                        buffer.flip();
                        log.info("从客户端获取数据 {}", StandardCharsets.UTF_8.decode(buffer));
                        selectionKey.cancel();
                        channel.close();
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
