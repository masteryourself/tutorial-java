package org.masteryourself.tutorial.nio.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p>description : ByteBuffer2String
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/7 10:44 PM
 */
public class ByteBuffer2String {

    public static void main(String[] args) {
        // 方法一: String 转 ByteBuffer
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());

        // 方法二: String 转 ByteBuffer, 它会自动切换到读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");

        // 方法三: String 转 ByteBuffer, 它会自动切换到读模式
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());

        // ByteBuffer 转 String
        buffer1.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buffer1));

        // ByteBuffer 转 String
        System.out.println(StandardCharsets.UTF_8.decode(buffer3));
    }

}
