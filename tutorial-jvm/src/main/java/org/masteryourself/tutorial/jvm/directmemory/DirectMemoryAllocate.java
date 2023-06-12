package org.masteryourself.tutorial.jvm.directmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>description : 直接内存的分配与回收
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/1 12:27 AM
 */
public class DirectMemoryAllocate {

    public static void main(String[] args) {
        int _1GB = 1 * 1024 * 1024 * 1024;
        Unsafe unsafe = getUnsafe();
        // 利用 unsafe 分配 1G 内存
        // 这个方法返回的是刚刚分配的内存地址, 需要结合 setMemory() 方法使用
        long memoryAddress = unsafe.allocateMemory(_1GB);
        unsafe.setMemory(memoryAddress, _1GB, (byte) 0);
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
