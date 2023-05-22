package org.masteryourself.tutorial.jvm.directmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : 直接内存溢出案例
 *
 * <p>blog : https://www.yuque.com/masteryoursef
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
