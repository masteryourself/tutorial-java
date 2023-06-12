package org.masteryourself.tutorial.concurrent.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * <p>description : AtomicReferenceFieldDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/3 18:06
 */
public class AtomicReferenceFieldDemo {

    public static void main(String[] args) {
        Student student = new Student();
        AtomicReferenceFieldUpdater<Student, String> updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        System.out.println(updater.compareAndSet(student, null, "张三"));
        System.out.println(student);
    }

    @Data
    public static class Student {
        public volatile String name;
    }

}
