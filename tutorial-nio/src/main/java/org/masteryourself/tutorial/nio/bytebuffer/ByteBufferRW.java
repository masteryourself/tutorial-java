package org.masteryourself.tutorial.nio.bytebuffer;

import java.nio.ByteBuffer;

/**
 * <p>description : ByteBufferRW
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/7 10:39 PM
 */
public class ByteBufferRW {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.put((byte) 'd');
        // 切换到读模式
        buffer.flip();
        // 先读取 2 个
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        // 标记索引
        buffer.mark();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        // 重置索引
        buffer.reset();
        // 再读 2 个
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }

}
