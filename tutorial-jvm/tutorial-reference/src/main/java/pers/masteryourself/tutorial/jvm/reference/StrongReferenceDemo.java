package pers.masteryourself.tutorial.jvm.reference;

/**
 * <p>description : StrongReferenceDemo，强引用
 * <p>当内存不足时，JVM 开始垃圾回收，对于强引用的对象，就算是出现了 OOM 也不会对该对象进行回收
 * <p>在 Java 中最常见的就是强引用，把一个对象赋值给一个引用变量，这个引用变量就是一个强引用。当一个对象被强引用变量引用时，它处于可达状态，它是不可能被回收的
 * <p>对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将强引用赋值为 null，一般认为就可以被垃圾回收了
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 12:52
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        // java.lang.Object@4554617c
        System.out.println(obj1);
        // java.lang.Object@4554617c
        System.out.println(obj2);
        obj1 = null;
        System.gc();
        // null
        System.out.println(obj1);
        // java.lang.Object@4554617c
        System.out.println(obj2);
    }

}
