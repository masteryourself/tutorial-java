package pers.masteryourself.tutorial.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : PhantomReferenceDemo，虚引用
 * <p>虚引用与其他几种引用都不同，虚引用并不会决定对象的生命周期
 * <p>如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能会被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列联合使用
 * <p>虚引用主要作用是跟踪对象被垃圾回收的状态，仅仅是提供了一种确保对象被 finalize 以后做某些事情的机制，
 * 即这个对象被垃圾收集器回收的时候收到系统的一个通知或者后续添加进一步的处理。
 * Java 技术允许使用 finalize() 方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 14:10
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws Exception {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> obj2 = new PhantomReference<>(obj1, referenceQueue);
        // java.lang.Object@4554617c
        System.out.println(obj1);
        // null
        System.out.println(obj2.get());
        // null
        System.out.println(referenceQueue.poll());
        obj1 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        // null
        System.out.println(obj1);
        // null
        System.out.println(obj2.get());
        // java.lang.ref.PhantomReference@74a14482
        System.out.println(referenceQueue.poll());
    }

}
