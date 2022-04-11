package org.masteryourself.tutorial.nio.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : ByteBuffer2String
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/7 10:44 PM
 */
public class ByteBuffer2String {

    public static void main(String[] args) {
        // String 转 ByteBuffer 方法一
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        ByteBufferUtil.debugAll(buffer1);

        // String 转 ByteBuffer 方法二, 它会自动切换到读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(buffer2);

        // String 转 ByteBuffer 方法三, 它会自动切换到读模式
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        ByteBufferUtil.debugAll(buffer3);

        // ByteBuffer 转 String
        buffer1.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buffer1));

        // ByteBuffer 转 String
        System.out.println(StandardCharsets.UTF_8.decode(buffer3));
    }

}
