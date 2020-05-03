package pers.masteryourself.tutorial.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * <p>description : SoftReferenceDemo, 弱引用
 * <p>软引用是一种相对强引用弱化了一些的引用，需要用 {@link SoftReference} 类来实现，可以让对象豁免一些垃圾收集
 * <p>对于只有软引用的对象来说，当系统内存充足时，不会被回收，当系统内存不足时，会被回收
 * <p>软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 13:42
 */
public class SoftReferenceDemo {

    /**
     * 启动参数添加 -Xms10m -Xmx10m
     *
     * @param args
     */
    public static void main(String[] args) {
        softReferenceEnough();
        System.out.println("==================我是分割符号==================");
        softReferenceNotEnough();
    }

    /**
     * 内存够用的情况下
     */
    private static void softReferenceEnough() {
        Object obj1 = new Object();
        SoftReference<Object> obj2 = new SoftReference<>(obj1);
        // java.lang.Object@4554617c
        System.out.println(obj1);
        // java.lang.Object@4554617c
        System.out.println(obj2.get());
        obj1 = null;
        // null
        System.out.println(obj1);
        // java.lang.Object@4554617c
        System.out.println(obj2.get());
    }

    /**
     * 内存不够用的情况下
     */
    private static void softReferenceNotEnough() {
        Object obj1 = new Object();
        SoftReference<Object> obj2 = new SoftReference<>(obj1);
        // java.lang.Object@74a14482
        System.out.println(obj1);
        // java.lang.Object@74a14482
        System.out.println(obj2.get());
        obj1 = null;
        try {
            // 这里 new 了一个 10M 的大对象，触发 OOM
            byte[] bytes = new byte[10 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // null
            System.out.println(obj1);
            // null
            System.out.println(obj2.get());
        }
    }

}
