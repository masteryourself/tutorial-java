package pers.masteryourself.tutorial.jvm.gc.gcroot;

/**
 * <p>description : GcRootDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 2:20
 */
public class GcRootDemo {

    /**
     * 方法区中静态属性引用的对象
     */
    private static Demo2 demo2;

    /**
     * 方法区中常量引用的对象
     */
    private final Demo3 demo3 = new Demo3();

    public static void main(String[] args) {
        method();
    }

    /**
     * demo1 就是栈中引用的对象
     */
    private static void method() {
        Demo1 demo1 = new Demo1();
    }

}
