package org.masteryourself.tutorial.jvm.directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : DirectBufferMemoryDemo
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/1 12:02 AM
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        try {
            while (true) {
                ByteBuffer memory = ByteBuffer.allocateDirect(100 * 1024 * 1024);
                list.add(memory);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
