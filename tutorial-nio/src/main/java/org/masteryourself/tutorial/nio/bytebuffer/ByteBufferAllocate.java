package org.masteryourself.tutorial.nio.bytebuffer;

import java.nio.ByteBuffer;

/**
 * <p>description : ByteBufferAllocate
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/7 10:38 PM
 */
public class ByteBufferAllocate {

    public static void main(String[] args) {
        // java.nio.HeapByteBuffer，Java 堆内存，读写效率低，受到 GC 影响
        System.out.println(ByteBuffer.allocate(10).getClass().getName());

        // java.nio.DirectByteBuffer, 直接内存，读写效率高（少一次拷贝），不会受到 GC 影响，分配效率低
        System.out.println(ByteBuffer.allocateDirect(10).getClass().getName());
    }

}
