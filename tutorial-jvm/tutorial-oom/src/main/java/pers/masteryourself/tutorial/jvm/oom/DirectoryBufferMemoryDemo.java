package pers.masteryourself.tutorial.jvm.oom;

import java.nio.ByteBuffer;

/**
 * <p>description : DirectoryBufferMemoryDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 15:24
 */
public class DirectoryBufferMemoryDemo {

    /**
     * VM 参数：-XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("堆外内存：" + (sun.misc.VM.maxDirectMemory() / 1024 / 1024) + "M");
        ByteBuffer.allocateDirect(10 * 1024 * 1024);
    }

}
