package pers.masteryourself.tutorial.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * <p>description : WeakReferenceDemo，软引用
 * <p>只要发生 GC 就会被回收
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/2 0:59
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object);
        // java.lang.Object@4554617c
        System.out.println(object);
        // java.lang.Object@4554617c
        System.out.println(weakReference.get());
        object = null;
        System.gc();
        // null
        System.out.println(object);
        // null
        System.out.println(weakReference.get());
    }

}
