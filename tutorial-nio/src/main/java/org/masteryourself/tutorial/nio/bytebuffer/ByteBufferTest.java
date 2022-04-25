package org.masteryourself.tutorial.nio.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>description : ByteBufferTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/7 6:28 PM
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        // 准备输入流
        try (FileChannel channel = new FileInputStream("file/tutorial-io/data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);
                log.info("一共读到 {} 个字节数", len);
                if (len == -1) {
                    break;
                }
                // 切换到读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    log.info("读取到内容 {}", (char) buffer.get());
                }
                // 切换到写模式
                buffer.clear();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
