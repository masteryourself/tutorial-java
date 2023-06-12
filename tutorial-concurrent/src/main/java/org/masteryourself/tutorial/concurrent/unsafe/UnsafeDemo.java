package org.masteryourself.tutorial.concurrent.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>description : UnsafeDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/12 18:29
 */
public class UnsafeDemo {

    public static void main(String[] args) throws Exception {
        Field id = Student.class.getDeclaredField("id");
        Field name = Student.class.getDeclaredField("name");
        // 获得成员变量的偏移量
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        long idOffset = unsafe.objectFieldOffset(id);
        long nameOffset = unsafe.objectFieldOffset(name);
        Student student = new Student();
        // 使用 cas 方法替换成员变量的值
        unsafe.compareAndSwapInt(student, idOffset, 0, 20);
        unsafe.compareAndSwapObject(student, nameOffset, null, "张三");
        System.out.println(student);
    }

    @Data
    public static class Student {

        volatile int id;

        volatile String name;

    }

}
