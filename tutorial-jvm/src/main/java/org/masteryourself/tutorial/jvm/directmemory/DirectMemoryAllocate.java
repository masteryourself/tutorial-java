package org.masteryourself.tutorial.jvm.directmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>description : DirectMemoryAllocate
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/1 12:27 AM
 */
public class DirectMemoryAllocate {

    public static void main(String[] args) {
        int ONE_G = 1 * 1024 * 1024 * 1024;
        Unsafe unsafe = getUnsafe();
        // 利用 unsafe 分配 1G 内存
        // 这个方法返回的是刚刚分配的内存地址, 需要结合 setMemory() 方法使用
        long memoryAddress = unsafe.allocateMemory(ONE_G);
        unsafe.setMemory(memoryAddress, ONE_G, (byte) 0);
        // 释放内存
        unsafe.freeMemory(memoryAddress);
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            // 静态变量不需要传入实例
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
