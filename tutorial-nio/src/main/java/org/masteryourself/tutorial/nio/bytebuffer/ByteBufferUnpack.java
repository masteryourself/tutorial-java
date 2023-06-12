package org.masteryourself.tutorial.nio.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : ByteBufferUnpack
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 12:32 AM
 */
@Slf4j
public class ByteBufferUnpack {

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello world\nI'm zhangsan\n Ho".getBytes());
        split(source);
        source.put("w are you\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        // 切换到读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int len = i - source.position() + 1;
                ByteBuffer target = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }
                ByteBufferUtil.debugAll(target);
                // 切换到读模式
                target.flip();
                log.info("拆解出信息 {}", StandardCharsets.UTF_8.decode(target));
            }
        }
        // 将未拆解的信息继续存入到 source 中
        source.compact();
    }

}
